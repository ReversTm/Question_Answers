package com.example.springbootpages.Repository;

import com.example.springbootpages.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<User, Integer> {
}
