package com.sh.lmg.trace;

/**
 * Created by liaomengge on 17/5/25.
 */
public final class IdConversion {

    public static String convertToString(long id) {
        return Long.toHexString(id);
    }

    public static long convertToLong(String lowerHex) {
        int length = lowerHex.length();
        if (length >= 1 && length <= 32) {
            int beginIndex = length > 16 ? length - 16 : 0;
            return convertToLong(lowerHex, beginIndex);
        } else {
            throw isntLowerHexLong(lowerHex);
        }
    }

    public static long convertToLong(String lowerHex, int index) {
        long result = 0L;

        for (int endIndex = Math.min(index + 16, lowerHex.length()); index < endIndex; ++index) {
            char c = lowerHex.charAt(index);
            result <<= 4;
            if (c >= 48 && c <= 57) {
                result |= (long) (c - 48);
            } else {
                if (c < 97 || c > 102) {
                    throw isntLowerHexLong(lowerHex);
                }

                result |= (long) (c - 97 + 10);
            }
        }

        return result;
    }

    static NumberFormatException isntLowerHexLong(String lowerHex) {
        throw new NumberFormatException(lowerHex + " should be a 1 to 32 character lower-hex string with no prefix");
    }
}
