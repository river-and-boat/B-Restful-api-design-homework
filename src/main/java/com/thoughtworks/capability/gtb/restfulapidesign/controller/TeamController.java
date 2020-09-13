package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.service.IntegrateService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
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
    public TeamEntity updateTeamName(@RequestBody @NotNull String oldName,
                                      @RequestBody @NotNull String newName) {
        return teamService.updateTeamName(oldName, newName);
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public void randomCreateTeams() {
        integrateService.randomTeamingSort();
    }
}
