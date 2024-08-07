package com.garrett.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class SteamUtils {

    public static byte[] handleInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        // 将数据转换为字节数组
        byte[] data = byteArrayOutputStream.toByteArray();
        System.out.println(new String(data, Charset.defaultCharset()));
        return data;
    }
}
