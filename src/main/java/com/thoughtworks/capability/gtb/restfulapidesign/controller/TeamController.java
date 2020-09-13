package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
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

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<TeamEntity> getGroups() {
        return teamService.getGroups();
    }

    @PutMapping("/group")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeamEntity updateGroupName(@RequestBody @NotNull String oldName,
                                      @RequestBody @NotNull String newName) {
        return teamService.updateGroupName(oldName, newName);
    }
}
