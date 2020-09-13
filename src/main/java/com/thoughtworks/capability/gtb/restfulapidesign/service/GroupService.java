package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.group.IGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final IGroupRepository groupRepository;

    public GroupService(IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> getGroups() {
        return groupRepository.getGroups();
    }

    public GroupEntity updateGroupName(String oldName, String newName) {
        return groupRepository.updateGroupName(oldName, newName);
    }
}
