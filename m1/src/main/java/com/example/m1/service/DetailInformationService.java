package com.example.m1.service;

import com.example.m1.models.DetailInformation;
import com.example.m1.models.People;
import com.example.m1.repository.DetailInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailInformationService {
    private final DetailInformationRepository detailInformationRepository;
    public DetailInformationService(DetailInformationRepository detailInformationRepository){
        this.detailInformationRepository=detailInformationRepository;
    }

    public DetailInformation findById(Long id){
        return detailInformationRepository.getOne(id);
    }

    public DetailInformation getDetailInformationByPeopleId(Long peopleId) {
        return detailInformationRepository.findByPeopleId(peopleId);
    }

    public List<DetailInformation> findAll(){
     return detailInformationRepository.findAll();
    }
    public DetailInformation saveInfo(DetailInformation detailInformation){
        return detailInformationRepository.save(detailInformation);
    }


    public void deleteInfo(Long id){
        detailInformationRepository.deleteById(id);
    }

}
