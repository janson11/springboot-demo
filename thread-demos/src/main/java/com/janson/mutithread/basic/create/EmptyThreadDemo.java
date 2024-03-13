package com.janson.mutithread.basic.create;

import com.janson.util.Print;
import com.janson.util.ThreadUtil;
import static com.janson.util.ThreadUtil.getCurThreadName;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2024/3/13 22:11
 **/
public class EmptyThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        // 使用Thread类创建和启动线程
        Thread thread = new Thread();
        Print.cfo("线程名称：" + thread.getName());
        Print.cfo("线程ID：" + thread.getId());
        Print.cfo("线程状态：" + thread.getState());
        Print.cfo("线程优先级：" + thread.getPriority());
        Print.cfo(getCurThreadName() + " 运行结束 ");
        thread.start();
        Print.cfo("线程状态：" + thread.getState());
        ThreadUtil.sleepMilliSeconds(10);
    }

}
