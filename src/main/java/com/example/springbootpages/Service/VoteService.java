package com.example.springbootpages.Service;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Entity.Vote;
import com.example.springbootpages.Repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {

    @Autowired
    private final VoteRepository voteRepository;

    public Vote getVoteByUserAndAnswer(User user, Answer answer){
        Vote vote = voteRepository.getVoteByUserAndAnswer(user,answer);
        return vote;
    }

    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }
}
