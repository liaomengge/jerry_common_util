package com.sh.lmg.http.http_client.builder;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by liaomengge on 17/6/7.
 */
public class GetBuilder extends HttpClientRequestBuilder<GetBuilder> {

    @Override
    protected HttpUriRequest buildHttpUriRequest() throws Exception {
        if (timeout <= 0) {
            timeout = DEFAULT_TIME_OUT;
        }
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).setConnectionRequestTimeout(timeout).build();
        url = appendParams(url, params);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);
        return httpGet;
    }
}
