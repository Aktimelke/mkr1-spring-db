package com.example.m1.repository;

import com.example.m1.models.DetailInformation;
import com.example.m1.models.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailInformationRepository extends JpaRepository<DetailInformation,Long> {
    DetailInformation findByPeopleId(Long peopleId);

}
