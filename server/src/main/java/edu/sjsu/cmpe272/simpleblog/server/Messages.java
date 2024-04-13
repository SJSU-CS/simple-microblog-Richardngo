package edu.sjsu.cmpe272.simpleblog.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.Base64;

@Entity
public class Messages
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Date;
    private String Author;
    private String Message;
    @Lob
    private String Attachement;
    @Lob
    private String Signature;

    protected Messages() {}

    public Messages(String date, String author, String message, String attachment, String signature)
    {
        this.Date = date;
        this.Author = author;
        this.Message = message;
        this.Attachement = attachment;
        this.Signature = signature;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDate()
    {
        return Date;
    }

    public void setDate(String date)
    {
        Date = date;
    }

    public String getAuthor()
    {
        return Author;
    }

    public void setAuthor(String author)
    {
        Author = author;
    }

    public String getMessage()
    {
        return Message;
    }

    public void setMessage(String message)
    {
        Message = message;
    }

    public String getAttachement()
    {
        return Attachement;
    }

    public void setAttachement(String attachement)
    {
        Attachement = attachement;
    }

    public String getSignature()
    {
        return Signature;
    }

    public void setSignature(String signature)
    {
        Signature = signature;
    }
}
