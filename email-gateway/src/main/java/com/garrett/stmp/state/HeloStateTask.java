package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;
import com.google.common.base.Strings;

import java.util.Set;

public class HeloStateTask implements StateTask {


    @Override
    public void handle(StmpHandler handler) {
        String line = handler.readFromClient();
        if (line == null) {
            return;
        }
        String[] args = line.trim().split(" ", 2);
        if (StmpState.HELO.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(args[0]))) {
            handler.writeToClient(StmpState.HELO.getCode());
            handler.setTask(new MailStateTask());
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }

    }
}
