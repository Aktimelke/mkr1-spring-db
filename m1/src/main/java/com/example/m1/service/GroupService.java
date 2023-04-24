package com.example.m1.service;

import com.example.m1.models.Group;
import com.example.m1.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    public Group findById(Long id){
        return groupRepository.getOne(id);
    }
    public List<Group> findAll(){
        return groupRepository.findAll();
    }
    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }
    public void deleteById(Long id){
        groupRepository.deleteById(id);
    }

}
