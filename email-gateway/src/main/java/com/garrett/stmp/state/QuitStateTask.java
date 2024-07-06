package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class QuitStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.warn("quit args is blank");
        }
        handler.getSmtpContext().setQuit(args);
        handler.writeToClient(SmtpState.QUIT.getCode());
        return false;
    }

    @Override
    public boolean checkArgument(String args) {
        return StringUtils.isNotBlank(args);
    }

}
