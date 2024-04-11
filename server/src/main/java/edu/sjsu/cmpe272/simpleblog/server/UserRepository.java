package edu.sjsu.cmpe272.simpleblog.server;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long>
{
    List<Users> findByUserName(String UserName);
    Users findById(long id);
}
