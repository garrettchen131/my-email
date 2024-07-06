package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class EhloStateTask implements StateTask {

    @Override
    public boolean handle(SmtpHandler handler, String args) {
        log.info("ehlo args: {}", args);
        if (!checkArgument(args)) {
            log.warn("ehlo args is blank");
        }
        handler.getSmtpContext().setHelo(args);
        handler.writeToClient(SmtpState.HELO.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String args) {
        return StringUtils.isNotBlank(args);
    }

}
