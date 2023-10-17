package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public List<User> getAllClients() {
        List<User> users = clientRepository.findAll();
        return users;
    }
    public User getClientById(int id){
        User user = clientRepository.getById(id);
        return user;
    }


}
