package com.garrett;

import cn.hutool.extra.mail.MailUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        MailUtil.send("myemail@myemail.com", ".测试", ".", false);

    }
}
