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

    private static List<GroupEntity> groups;

    static {
        groups = new LinkedList<>();
    }

    @Override
    public GroupEntity updateGroupName(String oldName, String newName) {
        Optional<GroupEntity> group = groups.stream()
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
        return groups;
    }

    @Override
    public GroupEntity saveGroup(GroupEntity groupEntity) {
        if (groupEntity.getName() == null || groupEntity.getName().equals("") ||
                groups.stream().anyMatch(s->s.getName().equals(groupEntity.getName()))) {
            throw new GroupException(ExceptionEnum.SAVE_GROUP_WITH_ILLEGAL_NAME);
        }
        groups.add(groupEntity);
        return groupEntity;
    }
}
