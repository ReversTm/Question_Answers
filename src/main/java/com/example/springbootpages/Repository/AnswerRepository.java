package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByClient(Client client);
}