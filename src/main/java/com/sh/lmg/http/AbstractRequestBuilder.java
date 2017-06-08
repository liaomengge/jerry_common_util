package com.sh.lmg.http;

import com.sh.lmg.log.MwLogger;
import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liaomengge on 17/6/7.
 */
public abstract class AbstractRequestBuilder<T extends AbstractRequestBuilder> {
    protected static Logger logger = new MwLogger(AbstractRequestBuilder.class);

    protected static final int DEFAULT_TIME_OUT = 5_000;

    protected String url;
    protected Map<String, String> params;

    public T url(String url) {
        this.url = url;
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

    public String appendParams(String url, Map<String, String> params) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sBuilder.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sBuilder = sBuilder.deleteCharAt(sBuilder.length() - 1);
        return sBuilder.toString();
    }

    protected void exceptionHandle(Exception e) {
        logger.error("调用服务失败, 请求地址[{}], 异常类型[{}], 错误原因[{}]", url, e.getClass(), e.getMessage());
    }

    public abstract String execute();
}
