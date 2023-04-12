package com.demo.web3j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateWalletAddress {
    private Log log = LogFactory.getLog(TestCreateWalletAddress.class);

    @Test
    public void generatePrivateKey() throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException, IOException, NoSuchProviderException {
        String password = "Pns8wGDTQzsrbZk8";

        File file = new File("keystore");
        if(!file.exists()){
            file.mkdir();
        }
        String walletFileName = WalletUtils.generateNewWalletFile(password, file);
        log.debug("walletFileName: " + walletFileName);

        File keystore = new File(file, walletFileName);

        Credentials credentials = WalletUtils.loadCredentials(password, keystore);
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String walletAddress  = credentials.getAddress();
        log.debug("walletAddress: " + walletAddress + " >> privateKey: "+ privateKey);
    }

    @Test
    public void getCredentialsFromPrivateKey() {
        String privateKey = "139fb8080bf7c1456d8474038222d9f35f03db21652e6dc0cfa682d64f484619";
        String walletAddress = "0x9f2fede4203e59d7fb68ed6c42d1041311992acf";

        Credentials credentials = Credentials.create(privateKey);
        assertEquals(walletAddress, credentials.getAddress());
        log.debug(credentials.getAddress());
    }
}
