package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestion(Question question);

}