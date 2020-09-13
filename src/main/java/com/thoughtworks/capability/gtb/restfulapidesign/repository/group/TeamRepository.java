package com.thoughtworks.capability.gtb.restfulapidesign.repository.group;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.TeamEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.TeamException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository implements ITeamRepository {

    private static List<TeamEntity> groupEntities;

    static {
        groupEntities = new LinkedList<>();
    }

    public static List<TeamEntity> getGroupEntities() {
        return groupEntities;
    }

    @Override
    public TeamEntity updateGroupName(String oldName, String newName) {
        Optional<TeamEntity> group = groupEntities.stream()
                .filter(s -> s.getName().equals(oldName))
                .findFirst();
        if (group.isPresent()) {
            group.get().setName(newName);
            return group.get();
        }
        throw new TeamException(ExceptionEnum.UPDATE_GROUP_NAME_NOT_EXIST);
    }

    @Override
    public List<TeamEntity> getGroups() {
        return groupEntities;
    }

    @Override
    public TeamEntity saveGroup(TeamEntity teamEntity) {
        if (teamEntity.getName() == null || teamEntity.getName().equals("") ||
                groupEntities.stream().anyMatch(s -> s.getName().equals(teamEntity.getName()))) {
            throw new TeamException(ExceptionEnum.SAVE_GROUP_WITH_ILLEGAL_NAME);
        }
        teamEntity.setId(generateGroupId());
        groupEntities.add(teamEntity);
        return teamEntity;
    }

    private synchronized Integer generateGroupId() {
        return (groupEntities.size() + 1);
    }
}
