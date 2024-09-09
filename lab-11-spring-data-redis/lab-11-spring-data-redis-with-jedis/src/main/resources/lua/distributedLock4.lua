--[[
分布式锁
场景：实现分布式系统中的锁机制，确保只有一个客户端可以执行关键操作。
示例：使用Lua脚本，你可以原子性地尝试获取锁，避免竞态条件，然后在完成后释放锁。]]
local key = KEYS[1] -- 获取锁的键名
local lockValue = ARGV[1] -- 获取锁的值
local lockTimeout = ARGV[2] -- 获取锁的超时时间
if redis.call("SET",localKey,lockValue,"NX","EX",lockTimeout) then
    -- 成功获取锁，执行关键操作
    -- 关键操作代码
    redis.call("DEL",localKey) -- 释放锁
    return true
else
    -- 锁已存在，说明已经有客户端在执行关键操作
    return false
end