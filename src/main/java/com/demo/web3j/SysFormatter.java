package com.demo.web3j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.web3j.utils.Convert;

/**
 * A thread safe formatter.
 *
 */
public class SysFormatter {
    private static ThreadLocal<SysFormatter> threadLocal = new ThreadLocal<>();

    /**
     * integer format
     *
     * @see Global#DEFAULT_INTEGER_FORMAT
     */
    private DecimalFormat integerFormat;

    /**
     * decimal format
     *
     * @see Global#DEFAULT_DECIMAL_FORMAT
     */
    private DecimalFormat decimalFormat;

    /**
     * costing format
     *
     * @see Global#DEFAULT_COSTING_FORMAT
     */
    private DecimalFormat costingFormat;

    /**
     * integer no comma format
     *
     * @see Global#DEFAULT_INTEGER_WITHOUT_COMMA_FORMAT
     */
    private DecimalFormat integerFormatWithoutComma;

    /**
     * decimal no comma format
     *
     * @see Global#DEFAULT_DECIMAL_WITHOUT_COMMA_FORMAT
     */
    private DecimalFormat decimalFormatWithoutComma;

    private DecimalFormat forexFormat;

    private DecimalFormat cryptoFormat;
    private DecimalFormat bwbFormat;

    private SimpleDateFormat serverDateFormat;
    private SimpleDateFormat serverDateTimeFormat;

    protected SysFormatter() {
        integerFormat = new DecimalFormat("#,###,###,###,###");
        decimalFormat = new DecimalFormat("#,###,###,###,##0.00");
        costingFormat = new DecimalFormat("#,###,###,###,##0.0000");
        integerFormatWithoutComma = new DecimalFormat("###");
        decimalFormatWithoutComma = new DecimalFormat("0.00");
        forexFormat = new DecimalFormat("###0.0000");
        cryptoFormat = new DecimalFormat("#,###,###,###,###,##0.00000000");
        bwbFormat = new DecimalFormat("#,###,###,###,###,##0.0000");

        serverDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        serverDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssa");
    }

    public static SysFormatter getInstance() {
        SysFormatter formatter = threadLocal.get();
        if (formatter == null) {
            formatter = new SysFormatter();
            threadLocal.set(formatter);
        }
        return formatter;
    }

    public static String formatInteger(int value) {
        return formatInteger(value, true);
    }

    public static String formatInteger(int value, boolean withComma) {
        if (withComma)
            return getInstance().integerFormat.format(value);
        else
            return getInstance().integerFormatWithoutComma.format(value);
    }

    public static String formatDecimal(double value) {
        return formatDecimal(value, true);
    }

    public static String formatDecimal(double value, boolean withComma) {
        if (withComma)
            return getInstance().decimalFormat.format(value);
        else
            return getInstance().decimalFormatWithoutComma.format(value);
    }

    public static String formatCosting(double value) {
        return getInstance().costingFormat.format(value);
    }

    public static String formatDate(Date date) {
        return getInstance().serverDateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        return getInstance().serverDateTimeFormat.format(date);
    }

    public static double ensureTwoDecimal(double value) {
        DecimalFormat df = getInstance().decimalFormat;
        Number number = null;
        try {
            number = df.parse(df.format(value));
            return number.doubleValue();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String formatForex(double value) {
        String s = getInstance().forexFormat.format(value);
        return _formatForex(s);
    }

    public static String formatForex(BigDecimal value) {
        String s = getInstance().forexFormat.format(value);
        return _formatForex(s);
    }

    private static String _formatForex(String value) {
        if (value.contains(".") && value.length() > 5) {
            value = value.substring(0, 5);
        } else if (value.length() > 4) {
            value = value.substring(0, 4);
        }

        if (value.endsWith("."))
            return value.substring(0, value.length() - 1);

        return value;
    }

    public static Date parseDate(String sdate) throws ParseException {
        return getInstance().serverDateFormat.parse(sdate);
    }

    public static Date parseDateTime(String sdateTime) throws ParseException {
        return getInstance().serverDateTimeFormat.parse(sdateTime);
    }

    public static BigDecimal fromWeiToEther(BigInteger value) {
        return fromWeiToEther(new BigDecimal(value));
    }

    public static BigDecimal fromWeiToEther(BigDecimal value) {
        return Convert.fromWei(value, Convert.Unit.ETHER);
    }

    public static BigDecimal fromWeiToGWei(BigInteger value) {
        return fromWeiToGWei(new BigDecimal(value));
    }

    public static BigDecimal fromWeiToGWei(BigDecimal value) {
        return Convert.fromWei(value, Convert.Unit.GWEI);
    }

    public static BigDecimal fromTokenDecimalToToken(BigInteger valueInTokenDecimal, int decimals) {
        return fromTokenDecimalToToken(new BigDecimal(valueInTokenDecimal), decimals);
    }

    public static BigDecimal fromTokenDecimalToToken(BigDecimal valueInTokenDecimal, int decimals) {
        return valueInTokenDecimal.divide(BigDecimal.TEN.pow(decimals));
    }

    public static BigInteger fromGWeiToWei(BigDecimal value) {
        return Convert.toWei(value, Convert.Unit.GWEI).toBigInteger();
    }

    public static BigDecimal fromEtherToWei(BigDecimal value) {
        return Convert.toWei(value, Convert.Unit.ETHER);
    }

    public static Date fromUtcTimestamp(int timestamp) {
        return new Date(timestamp * 1000);
    }

    public static Date fromUtcTimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    public static BigInteger toTokenDecimal(BigInteger valueInToken, int decimals) {
        return toTokenDecimal(new BigDecimal(valueInToken), decimals);
    }

    public static BigInteger toTokenDecimal(BigDecimal valueInToken, int decimals) {
        return valueInToken.multiply(BigDecimal.TEN.pow(decimals)).toBigInteger();
    }

    public static String formatCrypto(BigDecimal value) {
        return getInstance().cryptoFormat.format(value);
    }

    public static String formatBwb(BigDecimal value) {
        return getInstance().bwbFormat.format(value);
    }
}
