package edu.sjsu.cmpe272.simpleblog.client;

public class InvalidUserIDException extends RuntimeException
{
    InvalidUserIDException(String ID) {super("UserID contains invalid characters " + ID );}
}
