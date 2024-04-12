package edu.sjsu.cmpe272.simpleblog.client;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class UserConfig
{

    @JsonProperty("user")
    private String userName;
    @JsonProperty("private-key")
    private String privateKey;

    protected UserConfig() {}

    public UserConfig(String username, String pri_key)
    {
        this.userName = username;
        this.privateKey = pri_key;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPrivateKey()
    {
        return this.privateKey;
    }
    public void setPrivateKey(String privateKey)
    {
        this.privateKey = privateKey;
    }

    public PrivateKey getWorkingPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
    }
}