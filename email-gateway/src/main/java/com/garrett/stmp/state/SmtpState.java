package com.garrett.stmp.state;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SmtpState {

    INIT("INIT", "220"),
    HELO("HELO", "250"),
    EHLO("EHLO", "250"),
    MAIL("MAIL", "250"),
    RCPT("RCPT", "250"),
    DATA("DATA", "354"),
    END(".", "250"),
    QUIT("QUIT", "221"),

    ERROR(null, "500"),
    ;

    private final String commands;

    private final String code;

    private static final Map<String, SmtpState> STATE_MAP = new HashMap<>();

    static {
        for (SmtpState state : values()) {
            if (state.getCommands() != null) {
                STATE_MAP.put(state.getCommands(), state);
            }
        }
    }

    SmtpState(String commands, String code) {
        this.commands = commands;
        this.code = code;
    }

    public static SmtpState getState(String command) {
        if (command == null) {
            throw new IllegalArgumentException("command cannot be null");
        }
        return STATE_MAP.getOrDefault(command.toUpperCase(), ERROR);
    }
}
