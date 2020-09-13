package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private Gender gender;
    private String note;
    private GroupEntity groupEntity;
}
