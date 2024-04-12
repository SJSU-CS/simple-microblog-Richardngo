package edu.sjsu.cmpe272.simpleblog.client;


import java.security.PrivateKey;

public class Messages
{
    private String Date;
    private String Author;
    private String Message;
    private String Attachment;
    private String Signature;

    protected Messages()
    {
    }

    public Messages(String date, String author, String message, String attachment, String signature)
    {
        this.Date = date;
        this.Author = author;
        this.Message = message;
        this.Attachment = attachment;
        this.Signature = signature;
    }

    public Messages(String date, String author, String message)
    {
        this.Date = date;
        this.Author = author;
        this.Message = message;
        this.Attachment = null;
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

    public String getAttachment()
    {
        return Attachment;
    }

    public void setAttachment(String attachment)
    {
        Attachment = attachment;
    }

    public String getSignature()
    {
        return Signature;
    }

    public void setSignature(String signature)
    {
        Signature = signature;
    }

    public void generateSignature(PrivateKey privateKey)
    {

    }
}
