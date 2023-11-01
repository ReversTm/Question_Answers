package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findById(int id);
    List<Question> findByUserId(int userId);
}