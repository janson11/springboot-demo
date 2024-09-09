-- 第 2 到 4 行：判断 KEYS[1] 对应的 VALUE 是否为 ARGV[1] 值。如果不是（Lua 中不等于使用 ~=），则直接返回 0 表示失败。
if redis.call("GET", KEYS[1]) ~= ARGV[1] then
    return 0
end
-- 第 6 到 7 行：设置 KEYS[1] 对应的 VALUE  为新值 ARGV[2] ，并返回 1 表示成功。
redis.call("SET", KEYS[1], ARGV[2])
return 1