package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Gender gender;
    private String note;
    @JsonIgnore
    private TeamEntity teamEntity;
}
