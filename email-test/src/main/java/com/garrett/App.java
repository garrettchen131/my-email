package com.garrett;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

import javax.mail.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        String host = "imaphz.qiye.163.com";
        String mailStoreType = "imap";
        String username = "aliastest01@mttinvoicemail.hzqiye.ntesmail.com";
        String password = "MtInvoiceTest01";

        Properties properties = new Properties();
        properties.put("mail.store.protocol", mailStoreType);
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "143");
        properties.put("mail.imap.starttls.enable", "false");

        try {
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);
//            emailFolder.getMessage()
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                if ("欢迎您使用北京三快在线科技有限公司邮箱！".equals(message.getSubject()) && !message.isSet(Flags.Flag.DELETED)) {
                    System.out.println("delete");
                    message.setFlag(Flags.Flag.DELETED, true);
                }
            }

            emailFolder.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
