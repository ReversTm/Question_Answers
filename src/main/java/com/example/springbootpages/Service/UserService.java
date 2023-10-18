package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public User getUserById(int id){
        User user = userRepository.getById(id);
        return user;
    }


}
