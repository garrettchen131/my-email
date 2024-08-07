package com.garrett.server;

import com.garrett.listener.MyMessageListener;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

public class MySMTPServer implements Runnable {

    @Override
    public void run() {
        // 创建 SMTP 服务器
        SMTPServer smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(new MyMessageListener()));

        // 设置服务器端口
        smtpServer.setPort(25);

        // 启动服务器
        smtpServer.start();

        System.out.println("SMTPServer started on port 25");
    }
}
