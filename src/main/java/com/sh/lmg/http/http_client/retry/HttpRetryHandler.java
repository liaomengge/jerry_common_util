package com.sh.lmg.http.http_client.retry;

import com.sh.lmg.log.MwLogger;
import lombok.AllArgsConstructor;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by liaomengge on 17/1/3.
 */
@AllArgsConstructor
public class HttpRetryHandler implements HttpRequestRetryHandler {

    private static Logger logger = new MwLogger(HttpRetryHandler.class);

    private int reTryTimes = 3;//默认重试3次

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
        logger.info("retry times: " + executionCount);

        if (executionCount > reTryTimes - 1) {
            // Do not retry if over max retry count
            return false;
        }
        if (exception instanceof SSLException) {
            // SSL handshake exception
            return false;
        }
        if (exception instanceof UnknownHostException) {
            return false;
        }
        return true;
    }
}
