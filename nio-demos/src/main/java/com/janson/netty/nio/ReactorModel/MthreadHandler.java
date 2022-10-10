package com.janson.netty.nio.ReactorModel;

import com.janson.netty.common.config.SystemConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Proc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/9 3:48 下午
 */
@Slf4j
public class MthreadHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer input = ByteBuffer.allocate(SystemConfig.INPUT_SIZE);
    final ByteBuffer output = ByteBuffer.allocate(SystemConfig.SEND_SIZE);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    ExecutorService pool = Executors.newFixedThreadPool(2);
    static final int PROCESSING = 3;


    public MthreadHandler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        // 设置为非阻塞模式
        c.configureBlocking(false);

        // 仅仅取得选择键，绑定事件处理器，后设置感兴趣的IO事件
        sk = channel.register(selector, 0);

        // 将handler作为选择键的附件
        sk.attach(this);

        // 第二步,注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        // 唤醒事件查询线程了，在单线程模式下，这里没啥意义
        selector.wakeup();


    }

    boolean inputIsComplete() {
        return true;
    }

    boolean outputIsComplete() {
        return true;
    }

    boolean process() {
        return true;
    }

    @Override
    public void run() {
        try {
            if (state==READING) {
                read();
            } else if (state==SENDING) {
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    synchronized void read() throws IOException {
        channel.read(input);
        if (inputIsComplete()) {
            state = PROCESSING;
            pool.execute(new Processor());
        }

    }

    synchronized void send() throws IOException {
        channel.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }

    }

    class Processor implements Runnable {

        @Override
        public void run() {
            processAndHandOff();
        }
    }

    synchronized void processAndHandOff() {
        process();
        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE);

    }
}
