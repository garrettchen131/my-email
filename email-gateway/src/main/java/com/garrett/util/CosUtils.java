package com.garrett.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.region.Region;

import java.util.UUID;

public class CosUtils {

    private static final COSClient COS_CLIENT;

    private static final String BUCKET_NAME = "email-raw-1257562903";

    static  {
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        var tmpSecretId = "";
        var tmpSecretKey = "";
        var cred = new BasicCOSCredentials(tmpSecretId, tmpSecretKey);
        // 2 设置 bucket 的地域
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分
        var region = new Region("ap-guangzhou"); //COS_REGION 参数：配置成存储桶 bucket 的实际地域，例如 ap-beijing，更多 COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        var clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端
        COS_CLIENT = new COSClient(cred, clientConfig);
    }

    public static void save(String data) {
        COS_CLIENT.putObject(BUCKET_NAME, UUID.randomUUID().toString(), data);
    }
}
