package com.demo.web3j.contract;

import com.demo.web3j.BDUtil;
import com.demo.web3j.SysFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class EthUtil {
    public static Date convertDateFromQuantity(BigInteger qtyInSecond) {
        return new Date(qtyInSecond.longValue() * 1000);
    }

    public static BigDecimal calculateTransactionFeeInEther(int gasUsed, BigDecimal gasPriceInGWei) {
        gasPriceInGWei = BDUtil.roundUp2dp(gasPriceInGWei);
        BigInteger gasPriceInWei = SysFormatter.fromGWeiToWei(gasPriceInGWei);
        BigInteger transactionFeeInWei = gasPriceInWei.multiply(BigInteger.valueOf(gasUsed));
        return SysFormatter.fromWeiToEther(transactionFeeInWei);
    }
}
