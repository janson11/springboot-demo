local function luaSplit(str, sep)
local result = {}
if not str or not sep or type(str) ~= "string" or type(sep) ~= "string" then return result end
if #sep == 0 then return result end
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

local function incrementRedisHash(hashKey, field, amount)
redis.call('HINCRBY', hashKey, field, amount)
end

local timeAllowance = KEYS[1]
local periodAllowance = KEYS[2]
local grabTimekey = KEYS[3]
local teacherClassCapacityPrefix = KEYS[5]
local teacherTimeCapacityPrefix = KEYS[6]
local weekDayTime = ARGV[1]
local classPeriodId = ARGV[2]

local function checkCapacity(allowanceKey, currentFreeze, currentLoad, capacity)
return currentFreeze + currentLoad < capacity
end

if redis.call('EXISTS', grabTimekey) == 1 then
local value = redis.call('HGET', grabTimekey, handoverId)
if value == '1' or value == '2' then return value end
end

if redis.call('EXISTS', timeAllowance) == 0 then return 0 end

local timeFields = {'freeze', 'load', 'capacity'}
local timeValues = getRedisHash(timeAllowance, timeFields)

if not checkCapacity(timeAllowance, timeValues.freeze, timeValues.load, timeValues.capacity) then
return 0
end

if redis.call('EXISTS', periodAllowance) == 0 then return -1 end

local periodFields = {'freeze', 'load', 'capacity'}
local periodValues = getRedisHash(periodAllowance, periodFields)

if not checkCapacity(periodAllowance, periodValues.freeze, periodValues.load, periodValues.capacity) then
return -1
end

local teacherInternalIds = redis.call('HGET', timeAllowance, 'teacherInternalIds') or ''
if #teacherInternalIds == 0 then return -2 end

local teacherIds = luaSplit(teacherInternalIds, ',')

local bestTeacherId = '0'
local maxClassAllowance = 0

for _, teacherId in ipairs(teacherIds) do
local teacherClassCapacityKey = teacherClassCapacityPrefix .. ':' .. classPeriodId .. ':' .. teacherId
local teacherTimeCapacityKey = teacherTimeCapacityPrefix .. ':' .. teacherId .. ':' .. weekDayTime

local classFields = {'freeze', 'load', 'capacity'}
local classValues = getRedisHash(teacherClassCapacityKey, classFields)

if not checkCapacity(teacherClassCapacityKey, classValues.freeze, classValues.load, classValues.capacity) then
goto continue
end

local timeFields = {'freeze', 'load', 'capacity'}
local timeValues = getRedisHash(teacherTimeCapacityKey, timeFields)

if timeValues.freeze + timeValues.load < timeValues.capacity then
local classAllowance = classValues.capacity - (classValues.freeze + classValues.load)
if classAllowance > maxClassAllowance then
bestTeacherId = teacherId
maxClassAllowance = classAllowance
end
end

::continue::
end

if bestTeacherId == '0' then return -3 end
incrementRedisHash(teacherClassCapacityKey, 'freeze', 1)
incrementRedisHash(teacherTimeCapacityKey, 'freeze', 1)
incrementRedisHash(timeAllowance, 'freeze', 1)
incrementRedisHash(periodAllowance, 'freeze', 1)

redis.call('HSET', grabTimekey, handoverId, 1)
return bestTeacherId