package com.janson.netty.nio.ReactorModel;

import com.janson.netty.common.config.SystemConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.StandardSocketOptions;
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
public class MultiThreadEchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECIEVING = 0,SENDING =1;
    int state = RECIEVING;

    ExecutorService pool = Executors.newFixedThreadPool(4);
    static final int PROCESSING = 3;


    public MultiThreadEchoHandler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        // 设置为非阻塞模式
        channel.configureBlocking(false);
        channel.setOption(StandardSocketOptions.TCP_NODELAY,true);

        // 仅仅取得选择键，绑定事件处理器，后设置感兴趣的IO事件
        sk = channel.register(selector, 0);

        // 将handler作为选择键的附件
        sk.attach(this);

        // 第二步,注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        // 唤醒事件查询线程了，在单线程模式下，这里没啥意义
        selector.wakeup();
        log.info("新的连接注册完成");
    }


    @Override
    public void run() {
        // 异步任务。在独立的线程池中执行
        // 提交数据传输任务到线程池
        // 使得IO处理不在IO事件轮询线程中执行，在独立的线程池中执行。
        pool.execute(new AsyncTask());
    }


    private class AsyncTask implements Runnable {
        @Override
        public void run() {
            MultiThreadEchoHandler.this.asyncRun();
        }
    }

    // 异步任务 ，不在reactor线程中执行
    // 数据传输与业务处理任务，不在IO事件轮询线程中执行，在独立的线程池中执行
    private synchronized void asyncRun() {
        try {
            if (state==SENDING) {
                //写入通道
                channel.write(byteBuffer);
                // 写完后，准备开始从通道读，byteBuffer切换成写模式
                byteBuffer.clear();
                //写完后，注册read就绪事件
                sk.interestOps(SelectionKey.OP_READ);
                // 写完后。进入接收的状态
                state =RECIEVING;
            } else if (state==RECIEVING) {
                // 从通道读
                int length = 0;
                while ((length=channel.read(byteBuffer))>0){
                    log.info("MultiThreadEchoHandler read text:{}",new String(byteBuffer.array(),0,length));
                }
                //读完后，准备开始写入通道，byteBuffer切换成读模式
                byteBuffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                // 读完后，进入发送的状态
                state = SENDING;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
