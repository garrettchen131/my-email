package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class HeloStateTask implements StateTask {

    @Override
    public boolean handle(SmtpHandler handler, String args) {
        log.info("helo/ehlo args: {}", args);
        if (!checkArgument(args)) {
            log.warn("helo args is blank");
        }
        handler.getBuilder().helo(args);
        handler.writeToClient(SmtpState.HELO.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String args) {
        return StringUtils.isNotBlank(args);
    }

}
