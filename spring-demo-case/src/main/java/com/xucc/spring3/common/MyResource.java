package com.xucc.spring3.common;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

public class MyResource {
    public static Resource loadResource(String fileName) {
        if(fileName==null || fileName.length()==0) {
            return null;
        }
        // 磁盘文件载入
        if(fileName.startsWith("file:")) {
            return new FileSystemResource(fileName.substring("file:".length()));
        }

        // ClassPath文件载入
        if(fileName.startsWith("classpath:")) {
            return new FileSystemResource(fileName.substring("classpath:".length()));
        }

        // 网络文件载入
        if(fileName.startsWith("http")) {
            try {
                return new UrlResource(fileName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

