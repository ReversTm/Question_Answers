package com.example.springbootpages.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "surname")
    private String surname;

//    @OneToMany(mappedBy = "client")
//    private List<Question> questions;
//
//    // Поле для хранения ответа
//    @OneToMany(mappedBy = "client")
//    private List<Answer> answers;


//    @ManyToMany(cascade = {CascadeType.REFRESH,
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.DETACH})
//    @JoinTable(
//            name = "book_client",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "client_id")
//    )
//    private List<Client> clients = new ArrayList<>();
}
