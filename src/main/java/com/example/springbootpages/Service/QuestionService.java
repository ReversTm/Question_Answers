package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

   /* public List<Question> getAllQuestions(Client client) {
        List<Question> questions = questionRepository.findByClient(client);
        System.out.println(questions);
        return questions;
    }*/
    public List<Question> getAllQuestions(int id) {
        List<Question> questions = questionRepository.findByClientId(id);
//        System.out.println(questions);
        return questions;
    }

    public Question getQuestion(int questionId) {
        Question question = questionRepository.getById(questionId);
        return question;
    }
}
