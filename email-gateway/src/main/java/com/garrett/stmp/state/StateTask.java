package com.garrett.stmp.state;


import com.garrett.stmp.StmpHandler;

import java.util.Iterator;

public interface StateTask extends Iterator<StateTask> {

    void handle(StmpHandler handler);

}
