package com.janson.netty.nio.ReactorModel;

import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 多线程反应器
 * @Author: shanjian
 * @Date: 2022/10/9 3:34 下午
 */
@Slf4j
public class MultiThreadEchoServerReactor implements Runnable {

    public static void main(String[] args) throws IOException {
        MultiThreadEchoServerReactor multiThreadEchoServerReactor = new MultiThreadEchoServerReactor();
        multiThreadEchoServerReactor.startService();
    }

    private void startService() {
        // 子反应器对应一条线程
        new Thread(bossReactor).start();
        new Thread(workReactors[0]).start();
        new Thread(workReactors[1]).start();
    }


    Selector boosSelector = null;
    ServerSocketChannel serverSocket;
    AtomicInteger next = new AtomicInteger(0);
    Reactor bossReactor = null;
    // selectors集合，引入多个selector选择器
    Selector[] workSelectors = new Selector[2];
    //引入多个子反应器
    Reactor[] workReactors = null;


    MultiThreadEchoServerReactor() throws IOException {
        //初始化多个selector选择器

        // 用于监听新连接事件
        boosSelector = Selector.open();

        // 用于监听read、write事件
        workSelectors[0] = Selector.open();
        workSelectors[1] = Selector.open();

        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP, NioDemoConfig.SOCKET_SERVER_PORT);

        serverSocket.socket().bind(address);

        //非阻塞
        serverSocket.configureBlocking(false);


        //boosSelector 负责监听新连接事件，将serverSocket注册到boosSelector
        SelectionKey sk = serverSocket.register(boosSelector, SelectionKey.OP_ACCEPT);

        // 绑定Handler:新连接监控handler绑定到SelectionKey（选择键）
        sk.attach(new AcceptorHandler());

        // boosReactor反应器，处理新连接的bossSelector
        bossReactor = new Reactor(boosSelector);

        // 第一个子反应器，一个子反应器负责一个worker选择器
        Reactor workReactor1 = new Reactor(workSelectors[0]);

        // 第二个子反应器，一个子反应器负责一个worker选择器
        Reactor workReactor2 = new Reactor(workSelectors[1]);
        workReactors =new Reactor[]{workReactor1,workReactor2};

        log.info("服务端已经开始监听：{}", address);
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                boosSelector.select(1000);

                Set<SelectionKey> selected = boosSelector.selectedKeys();
                if (null == selected || selected.size() == 0) {
                    continue;
                }
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    // reactor负责dispatch接收的事件
                    SelectionKey sk = it.next();
                    it.remove();
                    dispatch(sk);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        // 调用之前attach绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }

    // handler 新连接处理器
    class AcceptorHandler implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                log.info("接收到一个新的连接");
                if (channel != null) {
                    int index = next.get();
                    log.info("选择器的编号：{}", index);
                    Selector selector = workSelectors[index];
                    new MultiThreadEchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (next.incrementAndGet() == workReactors.length) {
                next.set(0);
            }
        }
    }

    //反应器
    class Reactor implements Runnable {
        // 每条线程负责一个选择器的查询
        final Selector selector;

        public Reactor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    if (null == selectionKeys || selectionKeys.size() == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        dispatch(sk);
                    }
                    selectionKeys.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
