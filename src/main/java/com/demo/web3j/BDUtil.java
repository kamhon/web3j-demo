package com.demo.web3j;

import java.math.BigDecimal;

public class BDUtil {
    public BDUtil() {
    }

    public static boolean ge(BigDecimal number, BigDecimal number2) {
        return number.compareTo(number2) >= 0;
    }

    public static boolean e(BigDecimal number, BigDecimal number2) {
        if (number == null) {
            return number2 == null;
        } else {
            return number.compareTo(number2) == 0;
        }
    }

    public static boolean le(BigDecimal number, BigDecimal number2) {
        return number.compareTo(number2) <= 0;
    }

    public static boolean l(BigDecimal number, BigDecimal number2) {
        return number.compareTo(number2) < 0;
    }

    public static boolean g(BigDecimal number, BigDecimal number2) {
        return number.compareTo(number2) > 0;
    }

    public static boolean eZero(BigDecimal number) {
        return e(number, BigDecimal.ZERO);
    }

    public static boolean geZero(BigDecimal number) {
        return ge(number, BigDecimal.ZERO);
    }

    public static boolean gZero(BigDecimal number) {
        return g(number, BigDecimal.ZERO);
    }

    public static boolean leZero(BigDecimal number) {
        return le(number, BigDecimal.ZERO);
    }

    public static boolean lZero(BigDecimal number) {
        return l(number, BigDecimal.ZERO);
    }

    public static BigDecimal roundUp2dp(BigDecimal number) {
        return number.setScale(2, 4);
    }

    public static BigDecimal roundUp4dp(BigDecimal number) {
        return number.setScale(4, 4);
    }

    public static BigDecimal roundUp6dp(BigDecimal number) {
        return number.setScale(6, 4);
    }

    public static BigDecimal roundUp8dp(BigDecimal number) {
        return number.setScale(8, 4);
    }

    public static BigDecimal roundDown2dp(BigDecimal number) {
        return number.setScale(2, 1);
    }

    public static BigDecimal roundDown4dp(BigDecimal number) {
        return number.setScale(4, 1);
    }

    public static BigDecimal roundDown6dp(BigDecimal number) {
        return number.setScale(6, 1);
    }

    public static BigDecimal roundDown8dp(BigDecimal number) {
        return number.setScale(8, 1);
    }
}
