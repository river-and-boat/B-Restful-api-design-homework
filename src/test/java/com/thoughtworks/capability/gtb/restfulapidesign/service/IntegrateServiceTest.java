package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.student.StudentRepositoryImp;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.team.TeamRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntegrateServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private IntegrateService integrateService;

    @Autowired
    private TeamService teamService;

    @BeforeEach
    void init() {
        StudentRepositoryImp.getStudentEntities().clear();
        TeamRepositoryImp.getTeamEntities().clear();
        List<StudentEntity> students = new ArrayList<StudentEntity>() {
            {
                add(StudentEntity.builder().name("廖浚斌").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("沈乐棋").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("徐慧慧").gender(Gender.FEMALE).build());
                add(StudentEntity.builder().name("陈思聪").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("王江林").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("王登宇").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("杨思雨").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("江雨舟").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("廖篸").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("胡晓").gender(Gender.FEMALE).build());

                add(StudentEntity.builder().name("但杰").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("盖迈达").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("肖美琦").gender(Gender.FEMALE).build());
                add(StudentEntity.builder().name("黄云洁").gender(Gender.FEMALE).build());
                add(StudentEntity.builder().name("齐瑾浩").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("刘亮亮").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("肖逸凡").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("王作文").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("郭瑞凌").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("李明豪").gender(Gender.FEMALE).build());

                add(StudentEntity.builder().name("党泽").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("肖伊佐").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("允晨曦").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("李康宁").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("童世祁").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("马庆").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("商婕").gender(Gender.FEMALE).build());
                add(StudentEntity.builder().name("余榕").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("湛哲").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("董翔锐").gender(Gender.FEMALE).build());

                add(StudentEntity.builder().name("陈泰宇").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("赵允齐").gender(Gender.FEMALE).build());
                add(StudentEntity.builder().name("张柯").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("廖文强").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("何力").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("岳港").gender(Gender.MALE).build());
                add(StudentEntity.builder().name("凌风仪").gender(Gender.FEMALE).build());
            }
        };
        students.stream().forEach(s -> studentService.saveStudent(s));
    }

    @Test
    public void testDivideTeamsRandomly() {
        integrateService.randomTeamingSort();
        List<TeamEntity> teams = teamService.getTeams();

        assertEquals(6, teams.size());

        assertEquals(7, teams.get(0).getStudentsEntity().size());
        assertEquals(6, teams.get(1).getStudentsEntity().size());
        assertEquals(6, teams.get(2).getStudentsEntity().size());
        assertEquals(6, teams.get(3).getStudentsEntity().size());
        assertEquals(6, teams.get(4).getStudentsEntity().size());
        assertEquals(6, teams.get(5).getStudentsEntity().size());

        assertEquals("Team 1", teams.get(0).getName());
        assertEquals("Team 2", teams.get(1).getName());
        assertEquals("Team 3", teams.get(2).getName());
        assertEquals("Team 4", teams.get(3).getName());
        assertEquals("Team 5", teams.get(4).getName());
        assertEquals("Team 6", teams.get(5).getName());
    }
}