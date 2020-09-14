package com.thoughtworks.capability.gtb.restfulapidesign.repository.team;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;

import java.util.List;

public interface TeamRepository {
    TeamEntity updateTeamName(String oldName, String newName);
    List<TeamEntity> getTeams();
    TeamEntity saveTeam(TeamEntity teamEntity);
}
