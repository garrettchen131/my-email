package com.garrett.util;

import cn.hutool.json.JSONUtil;
import com.garrett.repository.SubscriptionRepository;
import okhttp3.*;

import java.io.IOException;

public class HttpUtils {

    private static final OkHttpClient httpClient = new OkHttpClient.Builder().build();

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");



    public static <T> void sendKey(T key) {

        String json = JSONUtil.toJsonStr(key);
        System.out.println("subscribe request json : " + json);

        Request request = new Request.Builder()
                .url("https://" + SubscriptionRepository.getUrl())
                .post(RequestBody.create(JSON, json))
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                } else {
                    System.err.println("Request failed: " + response);
                }
            }
        });
    }
}
