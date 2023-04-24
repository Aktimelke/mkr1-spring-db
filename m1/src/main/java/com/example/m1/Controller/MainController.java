package com.example.m1.Controller;

import com.example.m1.models.DetailInformation;
import com.example.m1.models.People;
import com.example.m1.service.DetailInformationService;
import com.example.m1.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    private final PeopleService peopleService;
    private final DetailInformationService detailInformationService;
    @Autowired
    public MainController( PeopleService peopleService,DetailInformationService detailInformationService){

        this.peopleService=peopleService;
        this.detailInformationService=detailInformationService;
    }

    @GetMapping("/")
    public String showMainPage() {
        return "index";
    }

    @GetMapping("/students")
    public String findAll(Model model){
        List<People> people = peopleService.findAll();
        model.addAttribute("people",people);
        return "students";
    }

    @GetMapping("student-update/{id}")
    public String updateStudentForm(@PathVariable("id") Long id, Model model){
        DetailInformation detailInformation = detailInformationService.getDetailInformationByPeopleId(id);
        if (detailInformation == null) {
            detailInformation = new DetailInformation();
            People people = peopleService.findById(id);
            detailInformation.setPeople(people);
        }
        model.addAttribute("detailInformation", detailInformation);
        return "student-update";
    }


    @PostMapping("student-update")
    public String updateStudent(DetailInformation detailInformation){


        detailInformationService.saveInfo(detailInformation);

        return "redirect:/students";
    }


}
