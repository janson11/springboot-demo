## SpringBoot Application共支持6种事件监听，按顺序分别是：

### 1、ApplicationStartingEvent：在Spring最开始启动的时候触发
### 2、ApplicationEnvironmentPreparedEvent：在Spring已经准备好上下文但是上下文尚未创建的时候触发
### 3、ApplicationPreparedEvent：在Bean定义加载之后、刷新上下文之前触发
### 4、 ApplicationStartedEvent：在刷新上下文之后、调用application命令之前触发
### 5、ApplicationReadyEvent：在调用applicaiton命令之后触发
### 6、ApplicationFailedEvent：在启动Spring发生异常时触发

## 另外：
### 1、ApplicationRunner和CommandLineRunner的执行在第五步和第六步之间
### 2、Bean的创建在第三步和第四步之间
### 3、在启动类中，执行SpringApplication.run()方法后的代码，会在第六步后执行
