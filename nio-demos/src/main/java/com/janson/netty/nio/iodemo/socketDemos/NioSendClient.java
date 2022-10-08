package com.janson.netty.nio.iodemo.socketDemos;

import com.janson.netty.common.util.IOUtil;
import com.janson.netty.common.util.ThreadUtil;
import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Description: 文件传送Client端
 * @Author: shanjian
 * @Date: 2022/10/8 2:24 下午
 */
@Slf4j
public class NioSendClient {

    public static void main(String[] args) {
        NioSendClient client = new NioSendClient();
        client.sendFile();
    }

    /**
     * 构造函数，与服务器建立连接
     */
    public NioSendClient() {

    }

    private Charset charset = Charset.forName("UTF-8");

    /**
     * 向服务端传输文件
     */
    public void sendFile() {
        try {
            // 发送小文件
            String srcPath = NioDemoConfig.SOCKET_SEND_FILE;
            File file = new File(srcPath);
            if (!file.exists()) {
                srcPath = IOUtil.getResourcePath(srcPath);
                log.debug("srcPath={}", srcPath);
                file = new File(srcPath);
                if (!file.exists()) {
                    log.debug("文件不存在");
                    return;
                }
            }

            FileChannel fileChannel = new FileInputStream(file).getChannel();

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
            socketChannel.socket().connect(new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP, NioDemoConfig.SOCKET_SERVER_PORT));
            // 设置非阻塞
            socketChannel.configureBlocking(false);
            log.debug("Client 成功连接服务端");

            while (!socketChannel.finishConnect()) {
                // 不断的自旋，等待，或者做一些其他的事情。
            }

            ByteBuffer fileNameByteBuffer = charset.encode(file.getName());
            ByteBuffer buffer = ByteBuffer.allocate(NioDemoConfig.SEND_BUFFER_SIZE);
            int fileNameLen = fileNameByteBuffer.remaining();
            buffer.clear();
            buffer.putInt(fileNameLen);

            // 切换到读模式，
            buffer.flip();
            socketChannel.write(buffer);
            log.info("Client 文件名称长度发送完成：{}", fileNameLen);

            // 发送文件名称
            socketChannel.write(fileNameByteBuffer);
            log.info("Client 文件名称发送完成：{}", file.getName());

            // 发送文件长度
            // 清空
            buffer.clear();
            buffer.putInt((int) file.length());

            // 切换到读模式，
            buffer.flip();

            // 写入文件长度
            socketChannel.write(buffer);
            log.info("Client 文件名称长度发送完成：{}", file.length());

            //发送文件内容
            log.debug("开始传送文件");
            int length = 0;
            long offset = 0;
            buffer.clear();

            while ((length = fileChannel.read(buffer)) > 0) {
                buffer.flip();
                socketChannel.write(buffer);
                offset += length;
                log.debug("| " + (100 * offset / file.length()) + "% |");
                buffer.clear();
            }

            // 等待一分钟关闭连接
            ThreadUtil.sleepSeconds(60);

            if (length == -1) {
                IOUtil.closeQuietly(fileChannel);
                socketChannel.shutdownOutput();
                IOUtil.closeQuietly(socketChannel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}
