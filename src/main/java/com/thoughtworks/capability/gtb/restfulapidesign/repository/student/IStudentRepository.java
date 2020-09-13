package com.thoughtworks.capability.gtb.restfulapidesign.repository.student;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    StudentEntity saveStudent(StudentEntity studentEntity);
    StudentEntity deleteStudent(String id);
    List<StudentEntity> getStudents(Optional<Gender> gender);
    StudentEntity getStudentById(String id);
    StudentEntity updateStudentInfo(StudentEntity studentEntity, String id);
}
