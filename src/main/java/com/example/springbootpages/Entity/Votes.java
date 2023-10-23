package com.example.springbootpages.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "votes")
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name="answer_id", referencedColumnName="id")
    private Answer answer;

    @Column(name = "vote")
    private int vote;
}