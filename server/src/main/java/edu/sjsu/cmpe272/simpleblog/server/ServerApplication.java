package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import edu.sjsu.cmpe272.simpleblog.server.MessageDTO;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
// do not change these base packages otherwise /ruok stops working!
@ComponentScan(basePackages = {"edu.sjsu.cmpe", "edu.sjsu.cmpe272.simpleblog.server"})
@RestController
public class ServerApplication extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(ServerApplication.class, args);
    }

    //Post request /messages/create
    //date, author, message, attachment (base64), signature (sha-256, sign with RSA)
    //return "message-id"
    @PostMapping (value = "/messages/create", consumes = "application/json", produces = "application/json")
    public Map<String, Object> postMessage(@RequestBody Map<String, Object> payload)
    {
        return payload;
    }
    //alternative mapping: https://stackoverflow.com/questions/29313687/trying-to-use-spring-boot-rest-to-read-json-string-from-post



    //Post List Messages /messages/list
    //limit (default of 10, max of 20), next -1 is last message.
    @PostMapping (value = "/messages/list", consumes = "application/json", produces = "application/json")
    public Map<String, Object> listMessage(@RequestBody Map<String, Object> payload)
    {
        return payload;
    }


    //Post create user /user/create
    // user (lowercase-a-z, and numbers only)
    //return "message: "welcome" if successful
    @PostMapping (value = "/user/create", consumes = "application/json", produces = "application/json")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> payload)
    {
        return payload;
    }

    //GET get public key /user/username/public-key
    //return the pem public key
    @GetMapping("/user/{username}/public-key")
    public Map<String, Object> getKey(@RequestBody Map<String, Object> payload)
    {
        return payload;
    }

}
