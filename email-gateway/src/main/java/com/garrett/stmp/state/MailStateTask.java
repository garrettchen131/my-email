package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import com.garrett.util.PatternUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.error("mail args check error: {}", args);
            throw new IllegalArgumentException("mail args check error");
        }
        handler.getBuilder().mail(args);
        handler.writeToClient(SmtpState.MAIL.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String arg) {
        if (!PatternUtils.isMailFrom(arg)) {
            return false;
        }
        var args = arg.split(":", 2);
        return "from".equalsIgnoreCase(args[0]);
    }

}
