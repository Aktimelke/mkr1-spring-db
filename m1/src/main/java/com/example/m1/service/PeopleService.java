package com.example.m1.service;

import com.example.m1.models.People;
import com.example.m1.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }
    public People findById(Long id){
       return peopleRepository.getOne(id);
    }
    public List<People> findAll(){
        return peopleRepository.findAll();
    }
    public List<People> findPeopleByGroupId(Long groupId) {
        return peopleRepository.findByGroupId(groupId);
    }
    public People savePeople(People people){
        return peopleRepository.save(people);
    }
    public void deleteById(Long id){
        peopleRepository.deleteById(id);
    }
}
