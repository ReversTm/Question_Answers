package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    @Autowired
    private final AnswerRepository answerRepository;
    @Transactional
    public List<Answer> getAllAnswers() {
        List<Answer> answers = answerRepository.findAll();
//        System.out.println(answers);
        return answers;
    }
    @Transactional
    public List<Answer> getByQuestion(Question question) {
        List<Answer> answers = answerRepository.findAllByQuestion(question);
//        System.out.println(answers);
        return answers;
    }

    @Transactional
    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }
    @Transactional
    public Answer getAnswerById(int answerId) {
        Answer answer = answerRepository.getById(answerId);
        return answer;
    }
    @Transactional
    public void update(Answer answer) {
        answerRepository.save(answer);
    }
    @Transactional
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
