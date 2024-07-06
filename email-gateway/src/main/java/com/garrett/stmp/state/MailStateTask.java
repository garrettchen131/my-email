package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import com.garrett.util.PatternUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class MailStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.error("mail args check error: {}", args);
            throw new IllegalArgumentException("mail args check error");
        }
        handler.getSmtpContext().setMail(args);
        handler.getSmtpContext().setFromEmail(getFromEmail(args));
        handler.writeToClient(SmtpState.MAIL.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String arg) {
        if (StringUtils.isBlank(arg) || !arg.substring(0, 5).equalsIgnoreCase("FROM:")) {
            return false;
        }
        return PatternUtils.isMailFrom(arg.substring(5).trim());
    }

    private String getFromEmail(String arg) {
        return PatternUtils.getEmail(arg);
    }

}
