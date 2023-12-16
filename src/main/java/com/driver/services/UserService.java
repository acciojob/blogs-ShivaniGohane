package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    ImageRepository imageRepository3;

    @Autowired
    BlogRepository blogRepository3;

    public User createUser(String username, String password){
        User user=new User(username,password);
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId){
        Optional<User> optionalUser=userRepository3.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getBlogList().clear();
            userRepository3.save(user);
        }
        userRepository3.deleteById(userId);


    }

    public User updateUser(Integer id, String password){
        Optional<User>optionalUser=userRepository3.findById(id);
        User user=optionalUser.get();
        user.setPassword(password);
        userRepository3.save(user);
        return user;

    }
}