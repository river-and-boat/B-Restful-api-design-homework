package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.TeamException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.group.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @AfterEach
    void cleanUp() {
        TeamRepository.getGroupEntities().clear();
    }

    @Test
    public void testSaveGroupWithEmptyName() {
        TeamEntity teamEntityWithEmptyName = TeamEntity.builder().name("")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        TeamException teamException = assertThrows(TeamException.class,
                () -> teamService.saveGroup(teamEntityWithEmptyName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the team name is illegal",
                teamException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testSaveGroupWithNullName() {
        TeamEntity teamEntityWithNullName = TeamEntity.builder()
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        TeamException teamException = assertThrows(TeamException.class,
                () -> teamService.saveGroup(teamEntityWithNullName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the team name is illegal",
                teamException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testSaveGroup() {
        TeamEntity teamEntity = TeamEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        TeamEntity savedEntity = teamService.saveGroup(teamEntity);
        assertEquals(1, savedEntity.getId());
        assertEquals("Team 1", savedEntity.getName());
        assertEquals("test", savedEntity.getNote());
    }

    @Test
    public void testRepeatGroupName() {
        TeamEntity teamEntity = TeamEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        teamService.saveGroup(teamEntity);

        TeamException teamException = assertThrows(TeamException.class,
                () -> teamService.saveGroup(teamEntity),
                "Expected doThing() to throw, but it didn't");
        assertEquals("the team name is illegal",
                teamException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testGetAllGroups() {
        TeamEntity group1 = TeamEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        TeamEntity group2 = TeamEntity.builder().name("Team 2")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        TeamEntity group3 = TeamEntity.builder().name("Team 3")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();

        teamService.saveGroup(group1);
        teamService.saveGroup(group2);
        teamService.saveGroup(group3);

        List<TeamEntity> groups = teamService.getGroups();

        assertEquals(3, groups.size());
        assertEquals(2, groups.get(1).getId());
        assertEquals("Team 3", groups.get(2).getName());
    }

    @Test
    public void testUpdateNotExistGroupName() {
        TeamEntity group = TeamEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        teamService.saveGroup(group);

        String oldName = "Team 2";
        String newName = "Team 3";

        TeamException teamException = assertThrows(TeamException.class,
                () -> teamService.updateGroupName(oldName, newName),
                "Expected doThing() to throw, but it didn't");
        assertEquals("can not find a team to update",
                teamException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testUpdateGroupName() {
        TeamEntity group = TeamEntity.builder().name("Team 1")
                .studentsEntity(new LinkedList<StudentEntity>())
                .note("test").build();
        teamService.saveGroup(group);

        String oldName = "Team 1";
        String newName = "Team 2";

        TeamEntity teamEntity = teamService.updateGroupName(oldName, newName);
        assertEquals("Team 2", teamEntity.getName());
    }
}