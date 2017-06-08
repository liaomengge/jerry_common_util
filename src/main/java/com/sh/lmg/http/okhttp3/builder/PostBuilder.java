package com.sh.lmg.http.okhttp3.builder;

import com.sh.lmg.http.okhttp3.MwOkHttp3Util;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by liaomengge on 17/6/7.
 */
public class PostBuilder extends OkHttpRequestBuilder<PostBuilder> {

    private String jsonParams = "";

    public PostBuilder(MwOkHttp3Util mwOkHttp3Util) {
        super(mwOkHttp3Util);
    }

    public PostBuilder json(String jsonParams) {
        this.jsonParams = jsonParams;
        return this;
    }

    @Override
    protected Request buildRequest() {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder reqBuilder = new Request.Builder().url(url);
        appendHeaders(reqBuilder, headers);

        if (StringUtils.isNotBlank(jsonParams)) {
            RequestBody requestBody = RequestBody.create(JSON, jsonParams);
            return reqBuilder.post(requestBody).build();
        }
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        this.appendParams(bodyBuilder, params);
        return reqBuilder.post(bodyBuilder.build()).build();
    }

    private void appendParams(FormBody.Builder builder, Map<String, String> params) {
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
    }
}
