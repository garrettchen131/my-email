package com.garrett.util;

import java.util.regex.Pattern;

public class PatternUtils {

    private PatternUtils() {
        throw new IllegalArgumentException();
    }

    private static final String MAIL_FROM_PATTERN_STR = "^\\w+:<\\w+@\\w+(\\.\\w+)*>$";

    private static final Pattern MAIL_FROM_PATTERN = Pattern.compile(MAIL_FROM_PATTERN_STR);

    public static boolean isMailFrom(String str) {
        return MAIL_FROM_PATTERN.matcher(str).matches();
    }
}
