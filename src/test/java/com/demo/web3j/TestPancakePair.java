package com.demo.web3j;

import com.demo.web3j.contract.PancakePair;
import com.demo.web3j.contract.PancakePairUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;

public class TestPancakePair {
    private Log log = LogFactory.getLog(TestPancakePair.class);

    @Test
    public void testCallDecimals() throws Exception {
        Web3j web3j = TestUtil.newMainnetWeb3j();
        PancakePair pancakePair = PancakePairUtil.loadReadonly(web3j, TestUtil.pancakePairContractAddress);
        BigInteger decimals = pancakePair.decimals().send();
        log.debug("decimals: " + decimals);
    }
}
