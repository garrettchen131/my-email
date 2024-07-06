package com.garrett.stmp.state;

import com.garrett.stmp.SmtpHandler;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataStateTask implements StateTask {
    @Override
    public boolean handle(SmtpHandler handler, String args) {
        if (!checkArgument(args)) {
            log.warn("data args is blank");
        }
        handler.getSmtpContext().setData(args);
        handler.writeToClient(SmtpState.DATA.getCode());

        var builder = new StringBuilder();
        while (true) {
            var line = handler.readFromClient();
            if (line == null) {
                return false;
            }
            var arg = line.trim();
            if (arg.length() == 1 && SmtpState.END.getCommands().equals(arg)) {
                break;
            }
            builder.append(arg).append('\n');
        }
        var rawEmailData = builder.toString();
        handler.getSmtpContext().setContent(rawEmailData);
        log.info("content : {}", rawEmailData);
        handler.writeToClient(SmtpState.END.getCode());
        return true;
    }

    @Override
    public boolean checkArgument(String args) {
        return Strings.isNullOrEmpty(args);
    }

}
