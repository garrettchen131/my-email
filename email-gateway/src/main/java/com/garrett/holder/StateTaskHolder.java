package com.garrett.holder;

import com.garrett.stmp.state.*;

import java.util.HashMap;
import java.util.Map;

public class StateTaskHolder {

    private static final Map<SmtpState, StateTask> STATE_TASK_MAP = new HashMap<>();

    static {
        STATE_TASK_MAP.put(SmtpState.INIT, new InitStateTask());
        STATE_TASK_MAP.put(SmtpState.HELO, new HeloStateTask());
        STATE_TASK_MAP.put(SmtpState.EHLO, new EhloStateTask());
        STATE_TASK_MAP.put(SmtpState.MAIL, new MailStateTask());
        STATE_TASK_MAP.put(SmtpState.RCPT, new RcptStateTask());
        STATE_TASK_MAP.put(SmtpState.DATA, new DataStateTask());
        STATE_TASK_MAP.put(SmtpState.QUIT, new QuitStateTask());
    }

    public static StateTask getTask(SmtpState state) {
        return STATE_TASK_MAP.get(state);
    }

}
