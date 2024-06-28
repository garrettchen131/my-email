package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;


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
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }

    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public StateTask next() {
        return new MailStateTask();
    }
}
