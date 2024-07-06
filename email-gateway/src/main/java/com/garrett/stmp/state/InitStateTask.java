package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitStateTask implements StateTask {

    @Override
    public boolean handle(SmtpHandler handler, String args) {
        log.info("init handler");
        handler.writeToClient(SmtpState.INIT.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String args) {
        throw new IllegalArgumentException("InitStateTask no such method");
    }

}
