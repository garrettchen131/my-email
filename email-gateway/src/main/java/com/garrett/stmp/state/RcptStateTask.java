package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import com.garrett.util.PatternUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RcptStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.error("rcpt args check error: {}", args);
            throw new IllegalArgumentException("rcpt args check error");
        }
        handler.getBuilder().rcpt(args);
        handler.writeToClient(SmtpState.RCPT.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String arg) {
        if (!PatternUtils.isMailFrom(arg)) {
            return false;
        }
        var args = arg.split(":", 2);
        return "to".equalsIgnoreCase(args[0]);
    }

}
