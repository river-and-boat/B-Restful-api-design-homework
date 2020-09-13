package com.thoughtworks.capability.gtb.restfulapidesign.repository.group;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;

import java.util.List;

public interface IGroupRepository {
    GroupEntity updateGroupName(String oldName, String newName);
    List<GroupEntity> getGroups();
    GroupEntity saveGroup(GroupEntity groupEntity);
}
