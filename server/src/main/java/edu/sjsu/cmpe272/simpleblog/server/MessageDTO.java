package edu.sjsu.cmpe272.simpleblog.server;
public class MessageDTO
{
    private String date;
    private String author;
    private String message;
    private String attachment;
    private String signature;


    public void setDate(String date)
    {
        this.date = date;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public void setAttachment(String attachment)
    {
        this.attachment = attachment;
    }
    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public String getDate()
    {
        return date;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getMessage()
    {
        return message;
    }
    public String getAttachment()
    {
        return attachment;
    }
    public String getSignature()
    {
        return signature;
    }
}

