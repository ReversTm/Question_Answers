package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Entity.Vote;
import com.example.springbootpages.Repository.VoteAnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteAnswerService {

    @Autowired
    private final VoteAnswerRepository voteAnswerRepository;

    public Vote getVoteByUserAndAnswer(User user, Answer answer){
        Vote vote = voteAnswerRepository.getVoteByUserAndAnswer(user,answer);
        return vote;
    }

    public void saveVote(Vote vote) {
        voteAnswerRepository.save(vote);
    }

    public void deleteVotesFromAnswer(Answer answer) {
        List<Vote> votes = voteAnswerRepository.findByAnswer(answer);
        voteAnswerRepository.deleteAll(votes);
    }
}
