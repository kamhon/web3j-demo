package com.demo.web3j;

import com.demo.web3j.contract.Erc20Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestErc20Token {
    private Log log = LogFactory.getLog(TestErc20Token.class);

    private ERC20 getErc20() {
        Web3j web3j = TestUtil.newWeb3j();
        Credentials credentials = TestUtil.getCredentials();
        RawTransactionManager transactionManager = TestUtil.newTransactionManager(web3j, credentials);
        StaticGasProvider staticGasProvider = TestUtil.getStaticGasProvider();
        return ERC20.load(TestUtil.erc20ContractAddress, web3j, transactionManager, staticGasProvider);
    }

    @Test
    public void testBalanceOf() throws Exception {
        ERC20 erc20 = Erc20Util.loadReadonly(TestUtil.newWeb3j(), TestUtil.erc20ContractAddress);
        BigInteger balanceInWei = erc20.balanceOf(TestUtil.walletAddress).send();

        BigDecimal balance = SysFormatter.fromWeiToEther(balanceInWei);
        log.debug("balance: " + balance);
    }

    @Test
    public void testTransfer1Token() throws Exception {
        ERC20 erc20 = getErc20();

        BigInteger amountInWei = SysFormatter.fromEtherToWei(BigDecimal.ONE).toBigInteger();

        TransactionReceipt transactionReceipt = erc20.transfer("0x7999f79eA96F7e9A3b0cA0C5a7253f40320AA60F", amountInWei).send();
        String txHash = transactionReceipt.getTransactionHash();

        boolean isOk = transactionReceipt.isStatusOK();
        if(isOk)
            log.debug("Transaction success >> txHash: " + txHash);
        else
            log.debug("Transaction failed >> txHash: " + txHash);
    }
}
