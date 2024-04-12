package edu.sjsu.cmpe272.simpleblog.client;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Users
{

    @JsonProperty("user")
    private String userName;
    @JsonProperty("public-key")
    private String publicKey;

    protected Users() {}

    public Users(String username,  String pub_key)
    {
        this.userName = username;
        this.publicKey = pub_key;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String publicKey()
    {
        return this.publicKey;
    }
    public void publicKey(String publicKey)
    {
        this.publicKey = publicKey;
    }
}