package com.sh.lmg.string;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    /**
     * 左填充
     *
     * @param str
     * @param length
     * @param ch
     * @return
     */
    public static String leftPad(String str, int length, char ch) {
        if (str.length() >= length) {
            return str;
        }
        char[] chs = new char[length];
        Arrays.fill(chs, ch);
        char[] src = str.toCharArray();
        System.arraycopy(src, 0, chs, length - src.length, src.length);
        return new String(chs);
    }


    public static String getValue(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str;
    }

    public static String getValue(Object str) {
        if (str == null) {
            return "";
        }

        if (StringUtils.isBlank(str.toString())) {
            return "";
        }
        return str.toString();
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isBlank(Object str) {
        return StringUtils.isBlank(getValue(str));
    }

    public static String addSlashes(String src) {
        if (src == null) {
            return "";
        }
        return src.replace("'", "\\'").replace("\"", "\\\"");
    }

    public static String urlencode(String src) {
        if (src == null) {
            return "";
        }
        try {
            return java.net.URLEncoder.encode(src, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return src;
    }

    public static String urldecode(String src) {
        if (src == null) {
            return "";
        }
        try {
            return java.net.URLDecoder.decode(src, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return src;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (!org.springframework.util.StringUtils.isEmpty(str)) {
            final Pattern p = Pattern.compile("[\t|\r|\n]");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String replaceQuota(String str) {
        String dest = "";
        if (!org.springframework.util.StringUtils.isEmpty(str)) {
            final Pattern p = Pattern.compile("[\"|\\\\]");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("'").replace("''", "'");
        }
        return dest;
    }

    public static String decodeUnicode(String theString) {

        char aChar;

        int len = theString.length();

        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len; ) {

            aChar = theString.charAt(x++);

            if (aChar == '\\') {

                aChar = theString.charAt(x++);

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = theString.charAt(x++);

                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);

                }

            } else

                outBuffer.append(aChar);

        }

        return outBuffer.toString();

    }

}
