package com.sh.lmg.http.okhttp3.builder;

import com.sh.lmg.http.AbstractRequestBuilder;
import com.sh.lmg.http.okhttp3.MwOkHttp3Util;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liaomengge on 17/6/7.
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> extends AbstractRequestBuilder<T> {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected Map<String, String> headers;
    protected Map<String, String> params;
    private MwOkHttp3Util mwOkHttp3Util;

    public OkHttpRequestBuilder(MwOkHttp3Util mwOkHttp3Util) {
        this.mwOkHttp3Util = mwOkHttp3Util;
    }

    public T headers(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T addHeaders(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return (T) this;
    }

    public T params(Map<String, String> params) {
        this.params = params;
        return (T) this;
    }

    public T addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return (T) this;
    }

    public void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) return;

        Headers.Builder headerBuilder = new Headers.Builder();
        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    @Override
    public String execute() {
        String result = StringUtils.EMPTY;
        if (mwOkHttp3Util != null) {
            Response response = null;
            try {
                response = mwOkHttp3Util.getOkHttpClient().newCall(this.buildRequest()).execute();
                if (response.isSuccessful()) {
                    result = response.body().string();
                } else {
                    logger.error("调用服务失败, 请求地址[{}], 错误码[{}]", url, response.code());
                }
            } catch (Exception e) {
                super.exceptionHandle(e);
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        }
        return result;
    }

    protected abstract Request buildRequest();
}
