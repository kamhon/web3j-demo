package com.demo.web3j.contract;

import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

public class Erc20Util {
    public static ERC20 loadReadonly(Web3j web3j, String contractAddress) {
        return ERC20.load(contractAddress, web3j, new ReadonlyTransactionManager(web3j, contractAddress), new DefaultGasProvider());
    }
}
