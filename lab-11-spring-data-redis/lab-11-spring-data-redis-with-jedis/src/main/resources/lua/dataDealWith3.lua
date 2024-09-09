--[[数据处理
--场景：需要对Redis中的数据进行复杂的处理，如统计、筛选、聚合等。
--示例：使用Lua脚本，可以在Redis中执行复杂的数据处理，而不必将数据传输到客户端进行处理，减少网络开销。]]
local keyPattern = ARGV[1]; -- 获取键名的匹配模式
local keys = redis.call("KEYS", keyPattern); -- 获取所有匹配的键名
local result = {}
for i,key in pairs(keys) do

end