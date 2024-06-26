package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;

public class DataStateTask implements StateTask {
    @Override
    public void handle(StmpHandler handler) {
        String line = handler.readFromClient();
        if (line == null) {
            return;
        }
        String[] args = line.trim().split(" ", 2);
        if (StmpState.DATA.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(args[0]))) {
            handler.writeToClient(StmpState.DATA.getCode());
            handler.setTask(new DataStateTask());
            getData(handler);
        } else {
            handler.writeToClient(StmpState.ERROR.getCode());
            handler.setTask(null);
        }

    }

    private void getData(StmpHandler handler) {
        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = handler.readFromClient();
            if (line == null) {
                return;
            }
            String text = line.trim();
            if (text.length() == 1 && StmpState.POINT.getCommands().stream().anyMatch(cmd -> cmd.equalsIgnoreCase(text))) {
                break;
            }
            builder.append(text).append('\n');
        }
        System.out.println(builder.toString());
        handler.writeToClient(StmpState.POINT.getCode());
    }
}
