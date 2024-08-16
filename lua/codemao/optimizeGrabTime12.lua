-- 辅助函数，用于分割字符串
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
-- 辅助函数，用于批量获取Redis哈希
local function getRedisHashes(prefix, keys, fields)
    local values = {}
    for _, key in ipairs(keys) do
        local hashFields = redis.call('HMGET', prefix .. ':' .. key, unpack(fields))
        values[key] = {}
        for i, field in ipairs(fields) do
            values[key][field] = tonumber(hashFields[i]) or 0
        end
    end
    return values
end
-- 辅助函数，用于批量更新Redis哈希
local function incrementRedisHashes(operations)
    local multi = redis.multi()
    for hashKey, increments in pairs(operations) do
        for field, amount in pairs(increments) do
            multi:hincrby(hashKey, field, amount)
        end
    end
    local _, err = multi:exec()
    if err then
        return nil, -4
    end
    return 1
end
-- 辅助函数，用于检查容量是否足够
local function checkCapacity(values)
    return values.freeze + values.load < values.capacity
end
-- 主函数
local function scriptMain()
    local timeAllowance = KEYS[1]
    local periodAllowance = KEYS[2]
    local grabTimekey = KEYS[3]
    local handoverId = ARGV[4]
    local teacherClassCapacityPrefix = KEYS[5]
    local teacherTimeCapacityPrefix = KEYS[6]
    local weekDayTime = ARGV[1]
    local classPeriodId = ARGV[2]
    -- 检查是否已经被抓取
    if redis.call('EXISTS', grabTimekey) == 1 then
        local value = redis.call('HGET', grabTimekey, handoverId)
        if value == '1' or value == '2' then
            return value
        end
    end
    -- 批量获取时间和周期容量
    local timeFields = {'freeze', 'load', 'capacity', 'teacherInternalIds'}
    local timeValues = getRedisHashes(timeAllowance, {''}, timeFields)['']
    local periodValues = getRedisHashes(periodAllowance, {''}, timeFields)['']
    if not checkCapacity(timeValues) or not checkCapacity(periodValues) then
        return not checkCapacity(timeValues) and 0 or -1
    end
    -- 获取教师ID列表
    local teacherIds = luaSplit(timeValues.teacherInternalIds, ',')
    if #teacherIds == 0 then
        return -2
    end
    -- 批量获取所有教师的容量信息
    local classFields = {'freeze', 'load', 'capacity'}
    local teacherClassCapacities = getRedisHashes(teacherClassCapacityPrefix, teacherIds, classFields)
    local teacherTimeCapacities = getRedisHashes(teacherTimeCapacityPrefix, teacherIds, classFields)
    local bestTeacherId = '0'
    local maxClassAllowance = 0
    for teacherId, classValues in pairs(teacherClassCapacities) do
        local timeValues = teacherTimeCapacities[teacherId]
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
    -- 构造需要更新的键
    local teacherClassCapacityKey = teacherClassCapacityPrefix .. ':' .. classPeriodId .. ':' .. bestTeacherId
    local teacherTimeCapacityKey = teacherTimeCapacityPrefix .. ':' .. bestTeacherId .. ':' .. weekDayTime
    local increments = {
        [timeAllowance] = {freeze = 1},
        [periodAllowance] = {freeze = 1},
        [teacherClassCapacityKey] = {freeze = 1},
        [teacherTimeCapacityKey] = {freeze = 1}
    }
    local status, errCode = incrementRedisHashes(increments)
    if not status then
        return errCode
    end
    redis.call('HSET', grabTimekey, handoverId, 1)
    return bestTeacherId
end
return scriptMain()