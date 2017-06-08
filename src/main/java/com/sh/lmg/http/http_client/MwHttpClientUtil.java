package com.sh.lmg.http.http_client;


import com.sh.lmg.http.AbstractHttpUtil;
import com.sh.lmg.http.http_client.builder.GetBuilder;
import com.sh.lmg.http.http_client.builder.PostBuilder;
import com.sh.lmg.http.okhttp3.MwOkHttp3Util;

/**
 * Created by liaomengge on 17/6/7.
 */
public class MwHttpClientUtil extends AbstractHttpUtil {

    private static MwHttpClientUtil mwHttpClientUtil;

    public static MwHttpClientUtil getInstance() {
        if (mwHttpClientUtil == null) {
            synchronized (MwOkHttp3Util.class) {
                if (mwHttpClientUtil == null) {
                    mwHttpClientUtil = new MwHttpClientUtil();
                }
            }
        }
        return mwHttpClientUtil;
    }

    public GetBuilder get() {
        return new GetBuilder();
    }

    public PostBuilder post() {
        return new PostBuilder();
    }
}
