package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;

public class RcptStateTask implements StateTask {
    @Override
    public void handle(StmpHandler handler) {
        String line = handler.readFromClient();
        if (line == null) {
            return;
        }
        String[] args = line.trim().split(" ", 2);
        if (StmpState.RCPT.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(args[0]))) {
            handler.writeToClient(StmpState.RCPT.getCode());
            handler.setTask(new DataStateTask());
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }
    }
}
