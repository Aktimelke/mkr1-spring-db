package com.example.m1.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "detailinformation")
public class DetailInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner")
    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Column(name = "status")
    private String status;
    @Column(name = "form1")
    private String form1;
    @Column(name = "form2")
    private String form2;
    @Column(name = "email")
    private String email;
    @Column(name = "bal")
    private float bal;
}
