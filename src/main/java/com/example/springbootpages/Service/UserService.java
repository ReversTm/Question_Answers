package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Transactional
    public void deleteUser(int id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
    }
    @Transactional
    public User getUserById(int id){
        User user = userRepository.getById(id);
        return user;
    }

    @Transactional
    public User getUserByUsername(String username) {
        User user = userRepository.getUserByName(username);
        return user;
    }
}
