package me.grace.springboot17;

import me.grace.springboot17.models.User;
import me.grace.springboot17.repositories.RoleRepo;
import me.grace.springboot17.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo)
    {
        this.userRepo=userRepo;
    }

    public User findbyEmail(String email){
        return userRepo.findByEmail(email);
    }

    public Long countByEmail(String email){
        return userRepo.countByEmail(email);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepo.findByRole("USER")));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepo.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepo.save(user);
    }

}
