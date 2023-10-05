package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Book;
import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Repository.BookRepository;
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
    public List<Client> getAllClients() {
        List<Client> books = clientRepository.findAll();
        System.out.println(books);
        return books;
    }
    public Client getBookByName(String name) {
        return clientRepository.getBookByName(name);
    }

    public void addNewBook(Client book) {
        clientRepository.save(book);
    }

    public void deleteBook(int id) {
        clientRepository.deleteById(id);
    }
}
