package com.sh.lmg.http.http_client.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liaomengge on 17/6/7.
 */
public class PostBuilder extends HttpClientRequestBuilder<PostBuilder> {

    private String jsonParams = "";

    public PostBuilder json(String jsonParams) {
        this.jsonParams = jsonParams;
        return this;
    }

    @Override
    protected HttpUriRequest buildHttpUriRequest() throws Exception {
        if (timeout <= 0) {
            timeout = DEFAULT_TIME_OUT;
        }
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).setConnectionRequestTimeout(timeout).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);

        if (StringUtils.isNotBlank(jsonParams)) {
            StringEntity entity = new StringEntity(jsonParams, encoding);
            entity.setContentType(jsonMediaType);
            httpPost.setEntity(entity);
            if (headers != null && headers.length > 0) {
                httpPost.setHeaders(headers);
            }
            return httpPost;
        }
        appendParams(httpPost);
        return httpPost;
    }

    private void appendParams(HttpPost httpPost) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            NameValuePair nameValuePair;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                nameValuePairs.add(nameValuePair);
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairs, encoding);
            formEntity.setContentType(formMediaType);
            httpPost.setEntity(formEntity);
        }
    }
}
