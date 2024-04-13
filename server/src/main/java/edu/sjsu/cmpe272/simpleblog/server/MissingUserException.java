package edu.sjsu.cmpe272.simpleblog.server;


public class MissingUserException extends RuntimeException
{
    MissingUserException(String username)
    {
        super("Could not find username " + username);
    }
}
