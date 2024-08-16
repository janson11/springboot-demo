
-- 辅助函数，用于分割字符串
local function luaSplit(str, sep)
local result = {}
if not str or not sep or type(str) ~= "string" or type(sep) ~= "string" then return result end
if #sep == 0 then return result end
local pattern = string.format("([^%s]+)", sep)
string.gsub(str, pattern, function(c) table.insert(result, c) end)
return result
end

-- 辅助函数，用于从Redis哈希表中批量获取字段值
local function batchGetHash(hashKeys, fields)
local allFields = {}
for _, key in ipairs(hashKeys) do
table.insert(allFields, key)
for _, field in ipairs(fields) do
table.insert(allFields, field)
end
end
local values = redis.call('HMGET', unpack(allFields))
local results = {}
local index = 1
for i = 1, #hashKeys do
results[hashKeys[i]] = {}
for j = 1, #fields do
results[hashKeys[i]][fields[j]] = tonumber(values[index]) or 0
index = index + 1
end
end
return results
end

-- 辅助函数，用于检查容量是否足够
local function checkCapacity(values, capacityField)
local total = values.freeze + values.load
return total < values[capacityField]
end

-- 辅助函数，用于原子性地增加Redis哈希表的字段值
local function incrementHashes(increments)
local multi = redis.multi()
for hashKey, fields in pairs(increments) do
for field, amount in pairs(fields) do
multi:hincrby(hashKey, field, amount)
end
end
return multi:exec()
end

-- 主逻辑
local function main()
-- 定义Redis键和字段
local timeAllowance = KEYS[1]
local periodAllowance = KEYS[2]
local grabTimekey = KEYS[3]
local handoverId = ARGV[4] -- 确保handoverId被定义
local weekDayTime = ARGV[1]
local classPeriodId = ARGV[2]
local teacherClassCapacityPrefix = KEYS[5]
local teacherTimeCapacityPrefix = KEYS[6]

local hashKeys = {timeAllowance, periodAllowance}
local fields = {'freeze', 'load', 'capacity'}

-- 批量获取Redis哈希表数据
local hashes = batchGetHash(hashKeys, fields)

-- 检查抢时段状态
if redis.call('EXISTS', grabTimekey) == 1 then
local value = redis.call('HGET', grabTimekey, handoverId)
if value and (value == '1' or value == '2') then return value end
end

-- 检查时段和班期承载资源是否存在以及容量是否足够
for _, hash in pairs(hashes) do
if not checkCapacity(hash, 'capacity') then
return hash == hashes[timeAllowance] and 0 or -1
end
end

-- 获取老师列表并检查容量
local teacherInternalIds = redis.call('HGET', timeAllowance, 'teacherInternalIds') or ''
local teacherIds = luaSplit(teacherInternalIds, ',')
if #teacherIds == 0 then return -2 end

local bestTeacherId = nil
local maxAllowance = -1
for _, teacherId in ipairs(teacherIds) do
local teacherClassCapacityKey = teacherClassCapacityPrefix .. ':' .. classPeriodId .. ':' .. teacherId
local teacherTimeCapacityKey = teacherTimeCapacityPrefix .. ':' .. teacherId .. ':' .. weekDayTime
local classValues = batchGetHash({teacherClassCapacityKey}, fields)[teacherClassCapacityKey]
local timeValues = batchGetHash({teacherTimeCapacityKey}, fields)[teacherTimeCapacityKey]

if checkCapacity(classValues, 'capacity') and checkCapacity(timeValues, 'capacity') then
local classAllowance = classValues.capacity - (classValues.freeze + classValues.load)
if classAllowance > maxAllowance then
bestTeacherId = teacherId
maxAllowance = classAllowance
end
end
end

if not bestTeacherId then return -3 end

-- 锁定资源，使用MULTI/EXEC事务来确保原子性
local increments = {
[teacherClassCapacityKey] = {freeze = 1},
[teacherTimeCapacityKey] = {freeze = 1},
[timeAllowance] = {freeze = 1},
[periodAllowance] = {freeze = 1}
}
incrementHashes(increments)

-- 设置抢时段成功
redis.call('HSET', grabTimekey, handoverId, 1)

return bestTeacherId
end

-- 执行主函数
return main()
