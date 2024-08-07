package com.garrett.repository;


import cn.hutool.json.JSONUtil;

public class SubscriptionRepository {

    private static String url;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        System.out.println("setUrl: " + url + ", originUrl: " + SubscriptionRepository.url);
        SubscriptionRepository.url = url;
    }

    public static void main(String[] args) {
        System.out.println(JSONUtil.toJsonStr(new SubscriptionRepository()));
    }
}
