package com.garrett.stmp.state;

import com.garrett.stmp.StmpHandler;

public class InitStateTask implements StateTask {


    @Override
    public void handle(StmpHandler handler) {
        handler.writeToClient(StmpState.INIT.getCode());
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public StateTask next() {
        return new HeloStateTask();
    }
}
