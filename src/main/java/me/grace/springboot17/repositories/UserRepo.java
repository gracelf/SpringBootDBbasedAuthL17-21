package me.grace.springboot17.repositories;

import me.grace.springboot17.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
