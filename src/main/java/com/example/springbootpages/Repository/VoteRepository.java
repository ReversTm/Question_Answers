package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Question findById(int id);
    List<Question> findByUserId(int userId);

    Vote getVoteByUserAndAnswer(User user, Answer answer);
}