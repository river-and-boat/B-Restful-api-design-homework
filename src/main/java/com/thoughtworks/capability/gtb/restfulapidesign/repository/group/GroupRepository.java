package com.thoughtworks.capability.gtb.restfulapidesign.repository.group;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepository implements IGroupRepository {

    private static List<GroupEntity> groupEntities;

    static {
        groupEntities = new LinkedList<>();
    }

    public static List<GroupEntity> getGroupEntities() {
        return groupEntities;
    }

    @Override
    public GroupEntity updateGroupName(String oldName, String newName) {
        Optional<GroupEntity> group = groupEntities.stream()
                .filter(s -> s.getName().equals(oldName))
                .findFirst();
        if (group.isPresent()) {
            group.get().setName(newName);
            return group.get();
        }
        throw new GroupException(ExceptionEnum.UPDATE_GROUP_NAME_NOT_EXIST);
    }

    @Override
    public List<GroupEntity> getGroups() {
        return groupEntities;
    }

    @Override
    public GroupEntity saveGroup(GroupEntity groupEntity) {
        if (groupEntity.getName() == null || groupEntity.getName().equals("") ||
                groupEntities.stream().anyMatch(s -> s.getName().equals(groupEntity.getName()))) {
            throw new GroupException(ExceptionEnum.SAVE_GROUP_WITH_ILLEGAL_NAME);
        }
        groupEntity.setId(generateGroupId());
        groupEntities.add(groupEntity);
        return groupEntity;
    }

    private synchronized Integer generateGroupId() {
        return (groupEntities.size() + 1);
    }
}
