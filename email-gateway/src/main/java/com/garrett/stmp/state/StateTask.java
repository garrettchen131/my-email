package com.garrett.stmp.state;


import com.garrett.stmp.SmtpHandler;


public interface StateTask {

    boolean handle(SmtpHandler handler, String args);

    boolean checkArgument(String args);
}
