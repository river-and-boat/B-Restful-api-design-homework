package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.team.ITeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final ITeamRepository groupRepository;

    public TeamService(ITeamRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<TeamEntity> getTeams() {
        return groupRepository.getTeams();
    }

    public TeamEntity updateTeamName(String oldName, String newName) {
        return groupRepository.updateTeamName(oldName, newName);
    }

    public TeamEntity saveTeam(TeamEntity teamEntity) {
        return groupRepository.saveTeam(teamEntity);
    }
}
