package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteQuestionRepository extends JpaRepository<VoteQuestion, Integer> {
    Question findById(int id);
    List<Question> findByUserId(int userId);

    VoteQuestion getVoteByUserAndQuestion(User user, Question question);
}