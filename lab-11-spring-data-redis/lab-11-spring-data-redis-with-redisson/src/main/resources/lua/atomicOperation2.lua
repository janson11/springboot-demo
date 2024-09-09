--[[
原子操作
场景：需要执行多个Redis命令作为一个原子操作，确保它们在多线程或多进程环境下不会被中断。
示例：使用Lua脚本，可以将多个命令组合成一个原子操作，如实现分布式锁、计数器、排行榜等。
]]
local key = KEYS[1] -- 获取键名
local value = ARGV[1] -- 获取参数值
local current = redis.call("GET", key) -- 获取当前值
if not current or tonumber(current) < tonumber(value) then
    -- 如果当前值不存在或新值更大，则设置新值
    redis.call("SET", key, value)
end