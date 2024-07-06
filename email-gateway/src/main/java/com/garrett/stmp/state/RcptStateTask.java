package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import com.garrett.util.PatternUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class RcptStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.error("rcpt args check error: {}", args);
            throw new IllegalArgumentException("rcpt args check error");
        }
        handler.getSmtpContext().setRcpt(args);
        handler.getSmtpContext().setToEmail(getToEmail(args));
        handler.writeToClient(SmtpState.RCPT.getCode());
        return true;
    }


    @Override
    public boolean checkArgument(String arg) {
        if (StringUtils.isBlank(arg) || !arg.substring(0, 3).equalsIgnoreCase("TO:")) {
            return false;
        }
        return PatternUtils.isMailFrom(arg.substring(3).trim());
    }

    private String getToEmail(String arg) {
        return PatternUtils.getEmail(arg);
    }

}
