package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestion(Question question);

    //Hello!
    //Test GitHub
}