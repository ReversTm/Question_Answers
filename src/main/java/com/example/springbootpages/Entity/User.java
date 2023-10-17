package com.example.springbootpages.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

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
