package com.sh.lmg.http.okhttp3.retry;

import com.sh.lmg.log.MwLogger;
import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * Created by liaomengge on 17/6/7.
 */
@AllArgsConstructor
public class RetryIntercepter implements Interceptor {

    private static Logger logger = new MwLogger(RetryIntercepter.class);

    private int reTryTimes = 3;//默认重试3次

    @Override
    public Response intercept(Chain chain) throws IOException {
        int retryNum = 0;
        Request request = chain.request();
        Response response = null;
        while (retryNum < reTryTimes) {
            try {
                response = chain.proceed(request);
                if (response.isSuccessful()) {
                    break;
                }
                retryNum++;
                logger.info("failed retry times: " + retryNum);
            } catch (IOException e) {
                retryNum++;
                logger.info("exception retry times: " + retryNum);
                if (retryNum == reTryTimes) {
                    throw e;
                }
            }
        }
        return response;
    }
}
