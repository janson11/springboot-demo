local function luaSplit(str, sep)
    local result = {}
    if not str or not sep or type(str) ~= "string" or type(sep) ~= "string" then
        return result
    end
    if #sep == 0 then
        return result
    end
    local pattern = string.format("([^%s]+)", sep)
    string.gsub(str, pattern, function(c) table.insert(result, c) end)
    return result
end
local function getRedisHash(hashKey, fields)
    local values = redis.call('HMGET', hashKey, unpack(fields))
    local result = {}
    for i, field in ipairs(fields) do
        result[field] = tonumber(values[i]) or 0
    end
    return result
end
local function incrementRedisHashes(operations)
    local multi = redis.multi()
    for hashKey, increments in pairs(operations) do
        for field, amount in pairs(increments) do
            multi:hincrby(hashKey, field, amount)
        end
    end
    return multi:exec()
end
local function checkCapacity(values)
    return values.freeze + values.load < values.capacity
end
local timeAllowance = KEYS[1]
local periodAllowance = KEYS[2]
local grabTimekey = KEYS[3]
local handoverId = ARGV[4]
local teacherClassCapacityPrefix = KEYS[5]
local teacherTimeCapacityPrefix = KEYS[6]
local weekDayTime = ARGV[1]
local classPeriodId = ARGV[2]
if redis.call('EXISTS', grabTimekey) == 1 then
    local value = redis.call('HGET', grabTimekey, handoverId)
    if value == '1' or value == '2' then
        return value
    end
end
local timeFields = {'freeze', 'load', 'capacity'}
local periodFields = {'freeze', 'load', 'capacity'}
local timeValues = getRedisHash(timeAllowance, timeFields)
local periodValues = getRedisHash(periodAllowance, periodFields)
if not checkCapacity(timeValues) or not checkCapacity(periodValues) then
    return not checkCapacity(timeValues) and 0 or -1
end
local teacherInternalIds = redis.call('HGET', timeAllowance, 'teacherInternalIds') or ''
local teacherIds = luaSplit(teacherInternalIds, ',')
if #teacherIds == 0 then
    return -2
end
local bestTeacherId = '0'
local maxClassAllowance = 0
for _, teacherId in ipairs(teacherIds) do
    local teacherClassCapacityKey = teacherClassCapacityPrefix .. ':' .. classPeriodId .. ':' .. teacherId
    local teacherTimeCapacityKey = teacherTimeCapacityPrefix .. ':' .. teacherId .. ':' .. weekDayTime
    local classValues = getRedisHash(teacherClassCapacityKey, timeFields)
    local timeValues = getRedisHash(teacherTimeCapacityKey, timeFields)
    if checkCapacity(classValues) and checkCapacity(timeValues) then
        local classAllowance = classValues.capacity - (classValues.freeze + classValues.load)
        if classAllowance > maxClassAllowance then
            bestTeacherId = teacherId
            maxClassAllowance = classAllowance
        end
    end
end
if bestTeacherId == '0' then
    return -3
end
local increments = {
    [timeAllowance] = {freeze = 1},
    [periodAllowance] = {freeze = 1},
    [teacherClassCapacityKey] = {freeze = 1},
    [teacherTimeCapacityKey] = {freeze = 1}
}
local _, err = incrementRedisHashes(increments)
if err then
    return -4
end
redis.call('HSET', grabTimekey, handoverId, 1)
return bestTeacherId