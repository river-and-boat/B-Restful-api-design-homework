package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.group.GroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @AfterEach
    void cleanUp() {
        GroupRepository.getGroupEntities().clear();
    }

    @Test
    public void testSaveGroupWithEmptyName() {
        GroupEntity groupEntityWithEmptyName = GroupEntity.builder().name("")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        GroupException groupException = assertThrows(GroupException.class,
                () -> groupService.saveGroup(groupEntityWithEmptyName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the group name is illegal",
                groupException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testSaveGroupWithNullName() {
        GroupEntity groupEntityWithNullName = GroupEntity.builder()
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        GroupException groupException = assertThrows(GroupException.class,
                () -> groupService.saveGroup(groupEntityWithNullName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the group name is illegal",
                groupException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testSaveGroup() {
        GroupEntity groupEntity = GroupEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        GroupEntity savedEntity = groupService.saveGroup(groupEntity);
        assertEquals(1, savedEntity.getId());
        assertEquals("Team 1", savedEntity.getName());
        assertEquals("test", savedEntity.getNote());
    }

    @Test
    public void testRepeatGroupName() {
        GroupEntity groupEntity = GroupEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        groupService.saveGroup(groupEntity);

        GroupException groupException = assertThrows(GroupException.class,
                () -> groupService.saveGroup(groupEntity),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the group name is illegal",
                groupException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testGetAllGroups() {
        GroupEntity group1 = GroupEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        GroupEntity group2 = GroupEntity.builder().name("Team 2")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        GroupEntity group3 = GroupEntity.builder().name("Team 3")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();

        groupService.saveGroup(group1);
        groupService.saveGroup(group2);
        groupService.saveGroup(group3);

        List<GroupEntity> groups = groupService.getGroups();

        assertEquals(3, groups.size());
        assertEquals(2, groups.get(1).getId());
        assertEquals("Team 3", groups.get(2).getName());
    }

    @Test
    public void testUpdateNotExistGroupName() {
        GroupEntity group = GroupEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        groupService.saveGroup(group);

        String oldName = "Team 2";
        String newName = "Team 3";

        GroupException groupException = assertThrows(GroupException.class,
                () -> groupService.updateGroupName(oldName, newName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("can not find a group to update",
                groupException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testUpdateGroupName() {
        GroupEntity group = GroupEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        groupService.saveGroup(group);

        String oldName = "Team 1";
        String newName = "Team 2";

        GroupEntity groupEntity = groupService.updateGroupName(oldName, newName);
        assertEquals("Team 2", groupEntity.getName());
    }
}