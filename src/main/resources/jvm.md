## JVM

### 1、Commons-Pool2总结



- ObjectPool :对象池
  - 最核心：GenericObjectPool、GenericKeyedObjectPool

-  Factory：创建&管理PooledObject
  -   一般要自己扩展
-  PooledObject:包装原有的对象，从而让对象池管理 
  -  一般用DefaultPooledObject