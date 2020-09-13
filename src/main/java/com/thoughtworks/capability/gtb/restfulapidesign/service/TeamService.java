package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.group.ITeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final ITeamRepository groupRepository;

    public TeamService(ITeamRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<TeamEntity> getGroups() {
        return groupRepository.getGroups();
    }

    public TeamEntity updateGroupName(String oldName, String newName) {
        return groupRepository.updateGroupName(oldName, newName);
    }

    public TeamEntity saveGroup(TeamEntity teamEntity) {
        return groupRepository.saveGroup(teamEntity);
    }
}
