package com.garrett.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

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

public class ParseUtils {


    private ParseUtils() {
        throw new IllegalArgumentException();
    }

    public static void getEmail(String rawEmail) throws MessagingException, IOException {
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

    public static Pair<String, String> parseLine(String line) {
        if (line == null) {
            return ImmutablePair.nullPair();
        }

        var args = line.split(" ", 2);

        if (args.length == 2) {
            return ImmutablePair.of(args[0], args[1]);
        }
        return ImmutablePair.of(args[0], null);
    }

}

