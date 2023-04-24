package com.example.m1.Test;

import com.example.m1.Controller.GroupController;
import com.example.m1.models.Group;
import com.example.m1.models.People;
import com.example.m1.service.GroupService;
import com.example.m1.service.PeopleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private PeopleService peopleService;

    @Mock
    private Model model;

    @InjectMocks
    private GroupController groupController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTest() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group());
        when(groupService.findAll()).thenReturn(groupList);

        String viewName = groupController.findAll(model);

        assertEquals("group-list", viewName);
        verify(groupService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("groups"), anyList());
    }

    @Test
    void createGroupFormTest() {
        String viewName = groupController.createGroupForm(new Group());

        assertEquals("group-create", viewName);
    }

    @Test
    void createGroupTest() {
        Group group = new Group();
        group.setId(1L);
        when(groupService.saveGroup(group)).thenReturn(group);

        String viewName = groupController.createGroup(group);

        assertEquals("redirect:/groups", viewName);
        verify(groupService, times(1)).saveGroup(group);
    }

    @Test
    void deleteGroupTest() {
        Long id = 1L;
        List<People> peopleList = new ArrayList<>();
        when(peopleService.findPeopleByGroupId(id)).thenReturn(peopleList);

        String viewName = groupController.deleteGroup(id, model);

        assertEquals("redirect:/groups", viewName);
        verify(peopleService, times(1)).findPeopleByGroupId(id);
        verify(groupService, times(1)).deleteById(id);
    }

    @Test
    void deleteGroupTest_hasPeople() {
        Long id = 1L;
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People());
        when(peopleService.findPeopleByGroupId(id)).thenReturn(peopleList);

        String viewName = groupController.deleteGroup(id, model);

        assertEquals("error-page", viewName);
        verify(peopleService, times(1)).findPeopleByGroupId(id);
        verify(model, times(1)).addAttribute(eq("errorMessage"), eq("Невозможно удалить группу, так как в ней есть студенты."));
        verifyNoInteractions(groupService);
    }


}

