package com.garrett.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

    private PatternUtils() {
        throw new IllegalArgumentException();
    }

    private static final String MAIL_FROM_PATTERN_STR = "^<\\w+@\\w+(\\.\\w+)*>$";
    private static final String MAIL_PATTERN_STR = "\\w+@\\w+(\\.\\w+)*";

    private static final Pattern MAIL_FROM_PATTERN = Pattern.compile(MAIL_FROM_PATTERN_STR);
    private static final Pattern MAIL_PATTERN = Pattern.compile(MAIL_PATTERN_STR);

    public static boolean isMailFrom(String str) {
        return MAIL_FROM_PATTERN.matcher(str).matches();
    }

    public static String getEmail(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        Matcher matcher = MAIL_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
