package com.example.m1.Controller;

import com.example.m1.Connect;
import com.example.m1.models.Group;
import com.example.m1.models.People;
import com.example.m1.service.DetailInformationService;
import com.example.m1.service.GroupService;
import com.example.m1.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Controller
public class GroupController {
    private final GroupService groupService;
    private final PeopleService peopleService;

    @Autowired
    public GroupController(GroupService groupService,PeopleService peopleService){
        this.groupService = groupService;
        this.peopleService=peopleService;
    }




    @GetMapping("/groups")
    public String findAll(Model model){
        List<Group> groups = groupService.findAll();
        model.addAttribute("groups",groups);
        return "group-list";
    }
@GetMapping("/group-create")
    public String createGroupForm(Group group){
        return "group-create";
    }
    @PostMapping("/group-create")
    public String createGroup(Group group){
        groupService.saveGroup(group);
        return "redirect:/groups";
    }


    @GetMapping("/group-delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        List<People> peopleList = peopleService.findPeopleByGroupId(id);
        if (!peopleList.isEmpty()) {
            // если есть связанные объекты People, то добавляем сообщение об ошибке в модель и возвращаем страницу с сообщением
            model.addAttribute("errorMessage", "Невозможно удалить группу, так как в ней есть студенты.");
            return "error-page";
        }
        // в противном случае удаляем группу и перенаправляем на список групп
        groupService.deleteById(id);
        return "redirect:/groups";
    }
    @GetMapping("/group-update/people-delete/{id}")
    public String deletePeople(@PathVariable("id")Long id){
        deleteInfo(id);
        peopleService.deleteById(id);
        return "redirect:/groups";
    }
    public void deleteInfo(Long id){
        Connection connection=null;
        Statement statement=null;
        Connect c=new Connect();
        connection=c.get_Connection();
        try{
            String query= String.format("delete from detailinformation WHERE id_owner='%d'",id);

            statement=connection.createStatement();
            statement.executeUpdate(query);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @GetMapping("group-update/{id}")
    public String updateGroupForm(@PathVariable("id")Long id , Model model){
        Group group = groupService.findById(id);
        model.addAttribute("group",group);

        List<People> people = peopleService.findPeopleByGroupId(id);
        model.addAttribute("people",people);

        return "/group-update";
    }
    @PostMapping("/group-update")
    public String updateGroup(Group group,People people){
        groupService.saveGroup(group);
        peopleService.savePeople(people);
        return "redirect:/groups";
    }

    @GetMapping("/people/new")
    public String showNewPeopleForm(Model model) {
        model.addAttribute("people", new People());
        model.addAttribute("groups", groupService.findAll());
        return "people-create";
    }

    @PostMapping("/people/save")
    public String savePeople(@ModelAttribute("people") People people) {
        peopleService.savePeople(people);
        return "redirect:/";
    }


}
