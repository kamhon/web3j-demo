package com.demo.web3j;

import okhttp3.OkHttpClient;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    public static String testnet_rpc = "https://data-seed-prebsc-1-s3.binance.org:8545/";
    public static String mainnet_rpc = "https://bsc-dataseed1.binance.org/";
    public static String walletAddress = "0x9f2fede4203e59d7fb68ed6c42d1041311992acf";
    public static String erc20ContractAddress = "0x4A39F075000BE8e7eec5C1c3a53C2b6609d5a918";

    public static String pancakePairContractAddress = "0x16b9a82891338f9ba80e2d6970fdda79d1eb0dae";

    public static String privateKey = "139fb8080bf7c1456d8474038222d9f35f03db21652e6dc0cfa682d64f484619";

    public static double gasPrice = 10; // 10 GWei (testnet)
    // private double gasPrice = 5; // 5 GWei (mainnet)

    public static long gasLimit = 200_000;
    public static long chainId = 97;

    public static Web3j newWeb3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        return Web3j.build(new HttpService(testnet_rpc, builder.build(), false));
    }

    public static Web3j newMainnetWeb3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        return Web3j.build(new HttpService(mainnet_rpc, builder.build(), false));
    }

    public static Credentials getCredentials() {
        return Credentials.create(privateKey);
    }

    public static StaticGasProvider getStaticGasProvider() {
        return new StaticGasProvider(
                SysFormatter.fromGWeiToWei(BigDecimal.valueOf(gasPrice)),
                BigInteger.valueOf(gasLimit)
        );
    }

    public static RawTransactionManager newTransactionManager(Web3j web3j, Credentials credentials) {
        return new RawTransactionManager(web3j, credentials, chainId, 300, 2000);
    }
}
