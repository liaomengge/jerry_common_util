package com.sh.lmg.http.okhttp3.builder;

import com.sh.lmg.http.okhttp3.MwOkHttp3Util;
import okhttp3.Request;

/**
 * Created by liaomengge on 17/6/7.
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> {

    public GetBuilder(MwOkHttp3Util mwOkHttp3Util) {
        super(mwOkHttp3Util);
    }

    @Override
    protected Request buildRequest() {
        if (params != null && !params.isEmpty()) {
            url = appendParams(url, params);
        }

        Request.Builder reqBuilder = new Request.Builder();
        appendHeaders(reqBuilder, headers);
        return reqBuilder.url(url).build();
    }

}
