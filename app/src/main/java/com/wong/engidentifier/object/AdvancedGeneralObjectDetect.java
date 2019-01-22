package com.wong.engidentifier.object;

import com.wong.engidentifier.http.auth.AuthService;
import com.wong.engidentifier.baidu.Base64Util;
import com.wong.engidentifier.baidu.FileUtil;
import com.wong.engidentifier.baidu.HttpUtil;

import java.net.URLEncoder;

/**
 * @author WongKyunban
 * description
 * created at 2018-12-29 下午5:51
 * @version 1.0
 *
 */
public class AdvancedGeneralObjectDetect {

    /**
     *
     * @param filePath 本地文件路径
     * @return
     */
    public static String detect(String filePath,ResultCallback callback) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
        try {


            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam + "&with_face=" + 1;

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
