package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Repository.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    @Autowired
    private final AnswerRepository answerRepository;
    public List<Answer> getAllAnswers() {
        List<Answer> answers = answerRepository.findAll();
//        System.out.println(answers);
        return answers;
    }

    public List<Answer> getByQuestion(Question question) {
        List<Answer> answers = answerRepository.findAllByQuestion(question);
//        System.out.println(answers);
        return answers;
    }


    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public Answer getAnswerById(int answerId) {
        Answer answer = answerRepository.getById(answerId);
        return answer;
    }

    public void update(Answer answer) {
        answerRepository.save(answer);
    }
//    public List<Answer> getAnswersFromOneClient(int id){
//        List<Answer> answer = answerRepository.findByClient(id);
//        return answer;
//    }


}
