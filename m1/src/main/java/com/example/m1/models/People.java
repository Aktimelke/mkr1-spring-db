package com.example.m1.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="people")
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_group")
    private Group group;

    @Column(name ="name")
    private String name;
    @Column(name ="surname")
    private String surname;
    @Column(name ="surname2")
    private String surname2;
    public Group getGroup() {
        return group;
    }

    // сеттер для поля group
    public void setGroup(Group group) {
        this.group = group;
    }
}
