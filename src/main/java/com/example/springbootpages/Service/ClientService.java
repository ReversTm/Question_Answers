package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Repository.AnswerRepository;
import com.example.springbootpages.Repository.ClientRepository;
import com.example.springbootpages.Repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        List<Client> books = clientRepository.findAll();
//        System.out.println(books);
        return books;
    }
    public Client getClientById(int id){
        Client client = clientRepository.getById(id);
        return client;
    }


}
