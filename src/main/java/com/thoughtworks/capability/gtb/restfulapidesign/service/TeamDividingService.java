package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.team.TeamRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.team.TeamRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamDividingService {

    private final static String TEAM_NAME_PREFIX = "Team ";
    private final static Integer TEAM_NUMBER = 6;

    private final StudentService studentService;
    private final TeamService teamService;

    public TeamDividingService(StudentService studentService,
                               TeamService teamService) {
        this.studentService = studentService;
        this.teamService = teamService;
    }

    public void randomTeamingSort() {
        teamService.getTeams().clear();
        List<StudentEntity> allStudents = studentService.getStudents(Optional.empty());
        Collections.shuffle(allStudents);
        int studentsNumber = allStudents.size();
        int overflowStudents = studentsNumber % TEAM_NUMBER;
        int previousNumber = 0;

        for (int groupId = 1; groupId <= TEAM_NUMBER; groupId++) {
            TeamEntity team = TeamEntity.builder().name(TEAM_NAME_PREFIX + groupId).build();
            int teamStudentNum = studentsNumber / TEAM_NUMBER;
            if (overflowStudents > 0) {
                teamStudentNum += 1;
                overflowStudents -= 1;
            }
            List<StudentEntity> teamStudentList = allStudents.stream()
                    .skip(previousNumber)
                    .limit(teamStudentNum)
                    .peek(s -> s.setTeamEntity(team)).collect(Collectors.toList());
            team.setStudentsEntity(teamStudentList);
            teamService.saveTeam(team);
            previousNumber += teamStudentNum;
        }
    }
}
