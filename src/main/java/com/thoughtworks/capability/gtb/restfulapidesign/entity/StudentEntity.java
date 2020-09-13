package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Gender gender;
    private String note;
    private GroupEntity groupEntity;
}
