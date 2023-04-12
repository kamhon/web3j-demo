package com.demo.web3j;

import com.demo.web3j.contract.Erc20Util;
import okhttp3.OkHttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class TestErc20Token {
    private Log log = LogFactory.getLog(TestErc20Token.class);

    private String testnet_rpc = "https://data-seed-prebsc-1-s3.binance.org:8545/";
    private String walletAddress = "0x9f2fede4203e59d7fb68ed6c42d1041311992acf";
    private String contractAddress = "0x4A39F075000BE8e7eec5C1c3a53C2b6609d5a918";

    private Web3j newWeb3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        return Web3j.build(new HttpService(testnet_rpc, builder.build(), false));
    }

    @Test
    public void testBalanceOf() throws Exception {
        ERC20 erc20 = Erc20Util.loadReadonly(newWeb3j(), contractAddress);
        BigInteger balanceInWei = erc20.balanceOf(walletAddress).send();

        BigDecimal balance = SysFormatter.fromWeiToEther(balanceInWei);
        log.debug("balance: " + balance);
    }
}
