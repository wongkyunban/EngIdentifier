package com.wong.engidentifier.object;

import com.wong.engidentifier.http.auth.AuthService;
import com.wong.engidentifier.baidu.Base64Util;
import com.wong.engidentifier.baidu.FileUtil;
import com.wong.engidentifier.baidu.HttpUtil;

import java.net.URLEncoder;

/**
 * @author WongKyunban
 * description
 * created at 2018-12-29 下午5:56
 * @version 1.0
 */
public class DishDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     * @param filePath  本地文件路径
     */

    public static String detect(String filePath,ResultCallback callback) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
        try {

            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam + "&top_num=" + 5;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            if(callback != null){
                callback.onResultCallback(result);
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
