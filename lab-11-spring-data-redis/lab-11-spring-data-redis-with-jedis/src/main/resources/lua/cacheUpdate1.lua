-- 缓存更新
--[[
场景：在缓存中存储某些数据，但需要定期或基于条件更新这些数据，同时确保在更新期间不会发生并发问题。
示例：使用Lua脚本，你可以原子性地检查数据的新鲜度，如果需要更新，可以在一个原子性操作中重新计算数据并更新缓存。

作者：上善若泪
链接：https://www.jianshu.com/p/2cf05ab2fef3
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
]]

local cacheKey = KEYS[1] --获取缓存键
local data = redis.call("GET", cacheKey) --尝试从缓存获取数据
if not data then
    -- 数据不在缓存中,重新计算并设置
    data = calculateData()
    redis.call("SET", cacheKey, data)
end
return data --返回数据

