package com.janson.netty.nio.ReactorModel;

import lombok.extern.slf4j.Slf4j;
import netscape.security.UserTarget;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/10/9 3:48 下午
 */
@Slf4j
public class EchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECIEVING = 0,SENDING =1;
    int state = RECIEVING;

    public EchoHandler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        // 设置为非阻塞模式
        c.configureBlocking(false);

        // 仅仅取得选择键，绑定事件处理器，后设置感兴趣的IO事件
        sk = channel.register(selector,0);

        // 将handler作为选择键的附件
        sk.attach(this);

        // 第二步,注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        // 唤醒事件查询线程了，在单线程模式下，这里没啥意义
        selector.wakeup();


    }

    @Override
    public void run() {
        try {
            // 发送状态
            if(state==SENDING) {
                // 写入通道
                channel.write(byteBuffer);
                // 写完后，准备开始从通道读，切换成写模式
                byteBuffer.clear();
                // 写完后，注册read就绪事件
                sk.interestOps(SelectionKey.OP_READ);
                // 写完后，进入接收的状态
                state =RECIEVING;
            } else if (state ==RECIEVING) {
                // 从通道读
                int length = 0;
                while ((length=channel.read(byteBuffer))>0) {
                    log.info("echo handler text:{}",new String(byteBuffer.array(),0,length));
                }
                //读完后，准备开始写入通道,byteBuffer切换读模式

                byteBuffer.flip();
                // 读完后，注册write就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                //读完后，进入发送的状态
                state = SENDING;
            }
        } catch (IOException e) {
            e.printStackTrace();
            sk.cancel();
            try {
                channel.finishConnect();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
