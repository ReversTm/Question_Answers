package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findById(int id);
}