package com.demo.web3j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;

import java.io.IOException;

public class TestJsonRpcCall {
    private Log log = LogFactory.getLog(TestJsonRpcCall.class);

    @Test
    public void testGetLatestBlockNumber() throws IOException {
        Web3j web3j = TestUtil.newWeb3j();

        long blockNumber = web3j.ethBlockNumber().send().getBlockNumber().longValue();
        log.debug("latestBlockNumber: " + blockNumber);
    }
}
