package com.example.administrator.alltest.postmedia;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/23 0023.
 * 重写的FileInputStream,主要是添加下载进度的一个监听方法
 *
 * 来源：http://www.2cto.com/kf/201502/376362.html
 */

public class CustomFileInputStream extends FileInputStream{

    private OnUploadListener listener;
    private int total,done;
    private double process;

    public CustomFileInputStream(File file) throws IOException {
        super(file);
        available();
    }

    public CustomFileInputStream(String name) throws IOException {
        super(name);
        available();
    }

    public CustomFileInputStream(FileDescriptor fdObj) throws IOException {
        super(fdObj);
        available();
    }

    @Override
    public int available() throws IOException {
        total=super.available();
        return total;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        done+=len;
        process=1.0*done/total;
        if(listener!=null){
            listener.onUpload(process);
        }
        return super.read(b,off,len);
    }

    //监听方法
    public void setOnUploadListener(OnUploadListener listener){
        this.listener=listener;
    }
}
