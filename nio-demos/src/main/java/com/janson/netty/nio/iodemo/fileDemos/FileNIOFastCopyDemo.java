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
 * @Description: 文件快速复制
 * @Author: shanjian
 * @Date: 2022/9/16 11:37 上午
 */
@Slf4j
public class FileNIOFastCopyDemo {

    public static void main(String[] args) {
        // 演示复制资源文件
        fastCopyResouceFile();
    }

    /**
     * 复制两个资源目录下的文件
     */
    public static void fastCopyResouceFile() {
        String srcPath = getSourceFile();

        String destPath = getDestFile();

        fastCopyFile(srcPath, destPath);
    }

    private static String getDestFile() {
        String destShortPath = NioDemoConfig.FILE_RESOURCE_DEST_PATH;
        String destPath = IOUtil.builderResourcePath(destShortPath);
        log.debug("destDecodePath={}",destPath);
        return destPath;
    }

    private static String getSourceFile() {
        String sourcePath = NioDemoConfig.FILE_RESOURCE_SRC_PATH;
        String srcPath = IOUtil.getResourcePath(sourcePath);
        log.debug("srcDecodePath={}",srcPath);
        return srcPath;
    }


    /**
     * 复制文件
     *
     * @param srcPath
     * @param destPath
     */
    public static void fastCopyFile(String srcPath, String destPath) {
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
                long size = inChannel.size();
                long pos = 0;
                long count =0;
                while (pos <size) {
                    // 每次复制最多1024个字节，没有就复制剩余的
                    count = size - pos >1024?1024:size-pos;
                    //复制内存，偏移量pos +count长度
                    pos+=outChannle.transferFrom(inChannel,pos,count);
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
