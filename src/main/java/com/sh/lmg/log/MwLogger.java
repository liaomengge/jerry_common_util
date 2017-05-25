package com.sh.lmg.log;

import com.sh.lmg.error.ThrowableUtil;
import com.sh.lmg.json.JsonUtil;
import com.sh.lmg.json.fastjson.FastJsonUtil;
import com.sh.lmg.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.util.StringUtils;


/**
 * Created by yangjunming on 7/9/16.
 */
public class MwLogger implements Logger {

    private final Logger logger;

    private final static String QUOTE = "\"";

    private final static String WRAPPER = "";

    private static final JsonUtil jsonUtil = new FastJsonUtil();


    private final Class<?> clazz;

    public MwLogger(Class clazz) {
        this.clazz = clazz;
        this.logger = LoggerFactory.getLogger(clazz);
    }

    private String replaceQuote(String src) {
        if (StringUtils.isEmpty(src)) {
            return "";
        }
        if (!StringUtils.isEmpty(QUOTE) && src.contains(QUOTE)) {
            return src.replace(QUOTE, "\\\"");
        }
        return StringUtil.replaceBlank(src);
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    public void trace(String msg) {
        logger.trace(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(WRAPPER + format(format, arguments) + WRAPPER);
    }

    public void trace(String msg, Throwable t) {
        logger.trace(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    public void debug(String msg) {
        logger.debug(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(WRAPPER + format(format, arguments) + WRAPPER);
    }

    public void debug(String msg, Throwable t) {
        logger.debug(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        logger.debug(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    public void info(String msg) {
        logger.info(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(WRAPPER + format(format, arguments) + WRAPPER);
    }

    public void info(String msg, Throwable t) {
        logger.info(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        logger.info(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    public void warn(String msg) {
        logger.warn(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    public void warn(String msg, Throwable t) {
        logger.warn(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        logger.warn(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }


    public void error(String msg) {
        logger.error(WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(WRAPPER + format(format, arguments) + WRAPPER);
    }

    public void error(String msg, Throwable t) {
        logger.error(WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, WRAPPER + replaceQuote(msg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        logger.error(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker, WRAPPER + replaceQuote(msg) + " " + ThrowableUtil.getStackTrace(t, true) + WRAPPER);
    }

    private String format(String template, Object... values) {
        template = template.replace("{}", "%s");
        return replaceQuote(String.format(template, values));
    }

}
