package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.GroupEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupEntity> getGroups() {
        return groupService.getGroups();
    }

    @PutMapping("/group")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GroupEntity updateGroupName(@RequestBody @NotNull String oldName,
                                       @RequestBody @NotNull String newName) {
        return groupService.updateGroupName(oldName, newName);
    }
}
