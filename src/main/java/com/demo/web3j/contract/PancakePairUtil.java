package com.demo.web3j.contract;

import org.web3j.protocol.Web3j;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

public class PancakePairUtil {
    public static PancakePair loadReadonly(Web3j web3j, String contractAddress) {
        return PancakePair.load(contractAddress, web3j, new ReadonlyTransactionManager(web3j, contractAddress), new DefaultGasProvider());
    }
}
