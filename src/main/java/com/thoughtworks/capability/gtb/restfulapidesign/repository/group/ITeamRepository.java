package com.thoughtworks.capability.gtb.restfulapidesign.repository.group;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;

import java.util.List;

public interface ITeamRepository {
    TeamEntity updateGroupName(String oldName, String newName);
    List<TeamEntity> getGroups();
    TeamEntity saveGroup(TeamEntity teamEntity);
}
