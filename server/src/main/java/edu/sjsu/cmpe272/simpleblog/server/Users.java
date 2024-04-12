package edu.sjsu.cmpe272.simpleblog.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.Base64;

@Entity
public class Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;
    @Lob
    private String PublicKey;

    protected Users() {}

    public Users(@JsonProperty("user")String username, @JsonProperty("public-key") String pub_key)
    {
        this.userName = username;
        this.setPublicKey(pub_key);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPublicKey()
    {
        return new String(Base64.getDecoder().decode(this.PublicKey));
    }

    public void setPublicKey(String publicKey)
    {
        String encodedKey = Base64.getEncoder().encodeToString(publicKey.getBytes());
        this.PublicKey = encodedKey;
    }
}


