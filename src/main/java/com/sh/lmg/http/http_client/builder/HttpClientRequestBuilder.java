package com.sh.lmg.http.http_client.builder;

import com.sh.lmg.http.AbstractRequestBuilder;
import com.sh.lmg.http.http_client.retry.HttpRetryHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by liaomengge on 17/6/7.
 */
public abstract class HttpClientRequestBuilder<T extends HttpClientRequestBuilder> extends AbstractRequestBuilder<T> {

    protected String encoding = "utf-8";
    protected String jsonMediaType = "application/json";
    protected String formMediaType = "application/x-www-form-urlencoded";

    protected int timeout;
    protected int reTryTimes;
    protected Header[] headers;

    public T timeout(int timeout) {
        this.timeout = timeout;
        return (T) this;
    }

    public T encode(String encoding) {
        this.encoding = encoding;
        return (T) this;
    }

    public T reTry(int reTryTimes) {
        this.reTryTimes = reTryTimes;
        return (T) this;
    }

    public T addHeaders(Header[] headers) {
        this.headers = headers;
        return (T) this;
    }

    protected CloseableHttpClient buildCloseableHttpClient() {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        if (reTryTimes > 0) {
            httpClientBuilder.setRetryHandler(new HttpRetryHandler(reTryTimes));
        }
        return httpClientBuilder.build();
    }

    protected abstract HttpUriRequest buildHttpUriRequest() throws Exception;

    @Override
    public String execute() {
        String result = StringUtils.EMPTY;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = this.buildCloseableHttpClient();
            CloseableHttpResponse httpResponse = httpClient.execute(this.buildHttpUriRequest());
            try {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    result = EntityUtils.toString(httpResponse.getEntity(), encoding);
                } else {
                    logger.error("调用服务失败, 请求地址[{}], 错误码[{}]", url, statusCode);
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            super.exceptionHandle(e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("关闭http client失败", e);
                }
            }
        }

        return result;
    }
}
