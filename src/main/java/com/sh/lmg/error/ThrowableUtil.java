package com.sh.lmg.error;

import com.sh.lmg.string.StringUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableUtil {

    /**
     * 获取异常的完整信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        return getStackTrace(t, true);
    }

    public static String getStackTrace(Throwable t, boolean removeLF) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            if (removeLF) {
                return StringUtil.replaceBlank(sw.toString());
            }
            return sw.toString();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
