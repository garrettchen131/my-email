package com.garrett.listener;

import cn.hutool.core.util.StrUtil;
import com.garrett.data.EmailInfoData;
import com.garrett.repository.SubscriptionRepository;
import com.garrett.util.CosUtils;
import com.garrett.util.HttpUtils;
import com.garrett.util.SteamUtils;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;

import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.Charset;

public class MyMessageListener implements SimpleMessageListener {
    @Override
    public boolean accept(String from, String recipient) {
        return true;
    }

    @Override
    public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
        try {

            byte[] dataBytes = SteamUtils.handleInputStream(data);

            MimeMessage message = new MimeMessage(null, new ByteArrayInputStream(dataBytes));
            System.out.println("From: " + from);
            System.out.println("To: " + recipient);
            System.out.println("Subject: " + message.getSubject());
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(dataBytes)));
            StringBuilder rawMessage = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                rawMessage.append(line).append("\n");
            }
            String rawEml = rawMessage.toString();
            System.out.println("rawEml: \n" + rawEml);
            String key = CosUtils.saveObj(rawEml, from, recipient, message.getSubject());
            if (StrUtil.isNotBlank(SubscriptionRepository.getUrl())) {
                EmailInfoData emailInfoData = new EmailInfoData();
                emailInfoData.setId(key);
                emailInfoData.setTo(recipient);
                emailInfoData.setFrom(from);
                emailInfoData.setSubject(message.getSubject());
                emailInfoData.setSentDate(message.getSentDate());
                emailInfoData.setReceiveDate(message.getReceivedDate());
                HttpUtils.sendKey(emailInfoData);
            }
            // 解析邮件内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
