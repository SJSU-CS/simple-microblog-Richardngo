package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

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

}
