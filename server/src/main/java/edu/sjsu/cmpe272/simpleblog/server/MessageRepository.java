package edu.sjsu.cmpe272.simpleblog.server;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Messages, Long>
{
    List<Messages> findAll();
    Messages findById(long id);
}
