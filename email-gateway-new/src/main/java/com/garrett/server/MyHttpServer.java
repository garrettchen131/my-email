package com.garrett.server;

import cn.hutool.core.util.StrUtil;
import com.garrett.repository.SubscriptionRepository;
import com.garrett.util.CosUtils;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.io.IOException;

public class MyHttpServer implements Runnable {

    @Override
    public void run() {
        Vertx vertx = Vertx.builder().build();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.post("/subscribe").handler(ctx -> {
            ctx.request().bodyHandler(body -> {
                JsonObject jsonObject = body.toJsonObject();
                String url = jsonObject.getString("url");
                System.out.println(url);
                SubscriptionRepository.setUrl(url);
                ctx.response().send("ok");
            });
        });

        router.get("/subscribe").handler(ctx -> {
            String json = "{\"url\": \"" + StrUtil.nullToEmpty(SubscriptionRepository.getUrl()) + "\"}";
            ctx.response()
                    .putHeader("content-type", "application/json")
                    .end(json);
        });

        router.get("/content").handler(ctx -> {
            String id = ctx.request().getParam("id");
            System.out.println(id);
            try {
                String rawEml = CosUtils.getObj(id);
                ctx.response()
                        .putHeader("content-type", "text/plain")
                        .end(rawEml);
            } catch (IOException e) {
                e.printStackTrace();
                ctx.response()
                        .putHeader("content-type", "text/plain")
                        .end(e.getMessage());
            }
        });


        httpServer.requestHandler(router).listen(8080, result -> {
            if (result.succeeded()) {
                System.out.println("HttpServer started on port 8080");
            } else {
                System.err.println("Failed to start Httpserver: " + result.cause());
            }
        });
    }
}
