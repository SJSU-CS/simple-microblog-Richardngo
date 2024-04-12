package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


@RestController
public class UserController
{
    private final UserRepository userRepository;
    UserController(UserRepository repository)
    {
        this.userRepository = repository;
    }

    //Post create user /user/create
    // user (lowercase-a-z, and numbers only)
    //return "message: "welcome" if successful
    @PostMapping(value = "/user/create", consumes = "application/json", produces = "application/json")
    public Map<String, String> createUser(@RequestBody Users user)
    {
        userRepository.save(user);

        Map<String,String> output = new HashMap<String, String>();
        output.put("message", "welcome");

        return output;
    }

    //GET public key /user/username/public-key
    //return the pem public key
    @GetMapping("/user/{username}/public-key")
    public Map<String, String> getKey(@PathVariable String username)
    {

        List<Users> buffer = userRepository.findByUserName(username);

        if(buffer.isEmpty()) {throw new MissingUserException(username);}
        //account for duplicate names later

        Map<String,String> output = new HashMap<String, String>();
        output.put("Public Key", buffer.get(0).getPublicKey());

        return output;
    }

}
