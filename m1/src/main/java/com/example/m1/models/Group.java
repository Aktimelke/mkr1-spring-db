package com.example.m1.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="groupss")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name;

}
