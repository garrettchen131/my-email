package com.garrett;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HutoolApp {
    public static void main(String[] args) {
//        MailUtil.send(Lists.newArrayList("myemail@myemail.com"), ".测试Test", ".Test", false);
//        String info = HttpUtil.get("http://119.91.252.56:8080/subscribe");
//        System.out.println(info);
        System.out.println(URLEncoder.encode("974212451/hello/d1d18ef74bb24fffaad182c4021ac1c9_subject.eml", StandardCharsets.UTF_8));
        System.out.println(URLUtil.encode("974212451/hello/d1d18ef74bb24fffaad182c4021ac1c9_subject.eml", StandardCharsets.UTF_8));
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", "974212451%2Fhello%2Fd1d18ef74bb24fffaad182c4021ac1c9_subject.eml");
//        HttpResponse execute = HttpRequest.get("http://119.91.252.56:8080/content")
//                .form(params)
//                .execute();
//        String result = execute.body();
//        System.out.println(result);
//        System.out.println();
    }

}
