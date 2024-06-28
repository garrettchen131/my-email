package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataStateTask implements StateTask {
    @Override
    public void handle(StmpHandler handler) {
        String line = handler.readFromClient();
        if (line == null) {
            return;
        }
        String[] args = line.trim().split(" ", 2);
        if (StmpState.DATA.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(args[0]))) {
            handler.writeToClient(StmpState.DATA.getCode());
            getData(handler);
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }

    }

    private void getData(StmpHandler handler) {
        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = handler.readFromClient();
            if (line == null) {
                return;
            }
            String text = line.trim();
            if (text.length() == 1 && StmpState.POINT.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(text))) {
                break;
            }
            builder.append(text).append('\n');
        }
        String rawEmail = builder.toString();
        System.out.println(rawEmail);
        try {
            getEmail(rawEmail);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        handler.writeToClient(StmpState.POINT.getCode());
    }

    private void getEmail(String rawEmail) throws MessagingException, IOException {
        // 将原始邮件字符串转换为输入流
        InputStream is = new ByteArrayInputStream(rawEmail.getBytes());

        // 设置邮件会话属性
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        // 解析邮件内容
        MimeMessage message = new MimeMessage(session, is);

        // 获取邮件主题
        String subject = MimeUtility.decodeText(message.getSubject());
        System.out.println("Subject: " + subject);

        // 解析邮件内容
        if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    String content = (String) bodyPart.getContent();
                    System.out.println("Content: " + content);
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public StateTask next() {
        return new QuitStateTask();
    }
}
