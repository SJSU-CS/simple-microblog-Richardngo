package edu.sjsu.cmpe272.simpleblog.server;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.lang.Math;
import java.util.stream.IntStream;

@RestController
public class MessageController
{

    private final MessageRepository repository;
    MessageController(MessageRepository repository)
    {
        this.repository = repository;
    }

    //Post request /messages/create
    //date, author, message, attachment (base64), signature (sha-256, sign with RSA)
    //return "message-id"
    @PostMapping("/messages/create")
    public Map<String, String> postMessage(@RequestBody Messages messsage)
    {
        repository.save(messsage);


        Map<String,String> output = new HashMap<>();
        output.put("message-id", Long.toString(messsage.getId()));

        return output;
    }

    //Post List Messages /messages/list
    //limit (default of 10, max of 20), next -1 is last message.
    @PostMapping (value = "/messages/list")
    public List<Messages> listMessage(@RequestBody Map<String, Object> payload)
    {
        //extracting JSON input
        int request_queue = Integer.parseInt(payload.getOrDefault("limit",10).toString());
        int next = Integer.parseInt(payload.get("next").toString());
        boolean reverse;

        //processing reserve
        if (next < 0) {reverse = true;}
        else {reverse = false;}

        //find next value
        final int next_index = Math.abs(next);

        //logic to sublist
        List<Messages> full_list = repository.findAll();
        if(reverse) {Collections.reverse(full_list);}

        //stole index search from https://stackoverflow.com/questions/54511107/how-to-get-the-index-of-object-by-its-property-in-java-list
        int index_location = IntStream.range(0,full_list.size()).filter(
                i -> full_list.get(i).getId() == next_index).findFirst().orElse(-1);

        //shameless stole this logic from: https://stackoverflow.com/questions/12099721/how-to-use-sublist
        List<Messages> buffer = full_list.subList(index_location,
                request_queue+index_location > full_list.size() ? full_list.size() : request_queue+index_location);


        return buffer;
    }


}
