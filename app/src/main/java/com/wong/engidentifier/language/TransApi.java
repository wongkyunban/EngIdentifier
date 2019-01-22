package com.wong.engidentifier.language;

import com.wong.engidentifier.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    /**
     * @param query 查询字符串
     * @return
     */
    public static void translate(String query, OkHttpGet.OkCallback okCallback) {

        transResult(query, "auto", "en",okCallback);
    }


    public static void transResult(String query, String from, String to,OkHttpGet.OkCallback okCallback) {
        Map<String, String> params = buildParams(query, from, to);
        String url = getUrlWithQuery(TRANS_API_HOST,params);
        OkHttpGet.getInstance().getDataAsyn(url,okCallback);
    }

    private static Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", Constants.TRANSLATION_APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = Constants.TRANSLATION_APP_ID + query + salt + Constants.TRANSLATION_SECRET_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

    public static String getUrlWithQuery(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }

        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }

        int i = 0;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null) { // 过滤空的key
                continue;
            }

            if (i != 0) {
                builder.append('&');
            }

            builder.append(key);
            builder.append('=');
            builder.append(encode(value));

            i++;
        }

        return builder.toString();
    }


    /**
     * 对输入的字符串进行URL编码, 即转换为%20这种形式
     *
     * @param input 原文
     * @return URL编码. 如果编码失败, 则返回原文
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }
}
