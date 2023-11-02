package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.*;
import com.example.springbootpages.Repository.VoteAnswerRepository;
import com.example.springbootpages.Repository.VoteQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteQuestionService {

    @Autowired
    private final VoteQuestionRepository voteQuestionRepository;

    public VoteQuestion getVoteByUserAndQuestion(User user, Question question){
        VoteQuestion vote = voteQuestionRepository.getVoteByUserAndQuestion(user,question);
        return vote;
    }

    public void saveVote(VoteQuestion vote) {
        voteQuestionRepository.save(vote);
    }
}
