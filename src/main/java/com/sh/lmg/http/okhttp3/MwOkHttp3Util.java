package com.sh.lmg.http.okhttp3;

import com.sh.lmg.http.AbstractHttpUtil;
import com.sh.lmg.http.okhttp3.builder.GetBuilder;
import com.sh.lmg.http.okhttp3.builder.PostBuilder;
import com.sh.lmg.http.okhttp3.retry.RetryIntercepter;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by liaomengge on 17/6/7.
 */
public class MwOkHttp3Util extends AbstractHttpUtil {

    private static MwOkHttp3Util mwOkHttp3Util;

    @Getter
    private OkHttpClient okHttpClient;
    @Setter
    private int timeout = 1_0;
    @Setter
    private int reTryTimes = 3;

    private MwOkHttp3Util() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RetryIntercepter(reTryTimes))
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .writeTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();
    }

    public static MwOkHttp3Util getInstance() {
        if (mwOkHttp3Util == null) {
            synchronized (MwOkHttp3Util.class) {
                if (mwOkHttp3Util == null) {
                    mwOkHttp3Util = new MwOkHttp3Util();
                }
            }
        }
        return mwOkHttp3Util;
    }

    public GetBuilder get() {
        return new GetBuilder(this);
    }

    public PostBuilder post() {
        return new PostBuilder(this);
    }
}
