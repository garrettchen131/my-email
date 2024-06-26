package com.garrett.stmp.state;

import lombok.Getter;

import java.util.Set;

@Getter
public enum StmpState {

    INIT(null, "220"),
    HELO(Set.of("HELO","EHLO"), "250"),
    MAIL(Set.of("MAIL"), "250"),
    RCPT(Set.of("RCPT"), "250"),
    DATA(Set.of("DATA"), "354"),
    POINT(Set.of("."), "250"),
    QUIT(Set.of("QUIT"), "221"),

    ERROR(null,"500")
    ;

    private final Set<String> commands;

    private final String code;

    private StmpState(Set<String> commands, String code) {
        this.commands = commands;
        this.code = code;
    }
}
