package com.janson.netty.nio.iodemo.fileDemos;

import com.janson.netty.common.util.IOUtil;
import com.janson.netty.nio.NioDemoConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/9/16 11:37 上午
 */
@Slf4j
public class FileNIOCopyDemo {

    public static void main(String[] args) {
        // 演示复制资源文件
        nioCopyResouceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    public static void nioCopyResouceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcPath = IOUtil.getResourcePath(sourcePath);

        String destShortPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destPath = IOUtil.builderResourcePath(destShortPath);

        nioCopyFile(srcPath, destPath);
    }


    /**
     * 复制文件
     *
     * @param srcPath
     * @param destPath
     */
    public static void nioCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            long startTime = System.currentTimeMillis();
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannle = null;
            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                inChannel = fis.getChannel();
                outChannle = fos.getChannel();
                int length = -1;
                ByteBuffer buf = ByteBuffer.allocate(1024);
                // 从输入通道读取到buf
                while ((length = inChannel.read(buf)) != -1) {
                    // 翻转buf,变成读模式
                    buf.flip();

                    int outLength = 0;
                    // 将buf写入到输出的通道
                    while ((outLength = outChannle.write(buf)) != 0) {
                        log.info("写入字节数:{}", outLength);
                    }

                    // 清楚buf，变成写模式
                    buf.clear();
                }

                // 强制刷新磁盘
                outChannle.force(true);

            } finally {
                // 关闭所有的可关闭对象
                IOUtil.closeQuietly(outChannle);
                IOUtil.closeQuietly(fos);
                IOUtil.closeQuietly(inChannel);
                IOUtil.closeQuietly(fis);
            }
            long endTime = System.currentTimeMillis();
            log.info("复制毫秒数：{}", (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
