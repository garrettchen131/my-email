package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;

public class QuitStateTask implements StateTask {
    @Override
    public void handle(StmpHandler handler) {
        String line = handler.readFromClient();
        if (line == null) {
            return;
        }
        String[] args = line.trim().split(" ", 2);
        if (StmpState.QUIT.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(args[0]))) {
            handler.writeToClient(StmpState.QUIT.getCode());
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public StateTask next() {
        return null;
    }
}
