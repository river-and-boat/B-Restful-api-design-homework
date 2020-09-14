package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GroupNameVO;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.service.IntegrateService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class TeamController {

    private final TeamService teamService;

    private final IntegrateService integrateService;

    public TeamController(TeamService teamService, IntegrateService integrateService) {
        this.teamService = teamService;
        this.integrateService = integrateService;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamEntity> getGroups() {
        return teamService.getTeams();
    }

    @PutMapping("/group")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeamEntity updateTeamName(@RequestBody @Valid GroupNameVO groupNameVO) {
        return teamService.updateTeamName(groupNameVO.getOldName(), groupNameVO.getNewName());
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public void randomCreateTeams() {
        integrateService.randomTeamingSort();
    }
}
