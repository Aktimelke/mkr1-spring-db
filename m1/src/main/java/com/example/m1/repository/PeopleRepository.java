package com.example.m1.repository;

import com.example.m1.models.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People,Long> {
    List<People> findByGroupId(Long groupId);
}
