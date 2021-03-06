package com.thoughtworks.capability.gtb.restfulapidesign.repository.student;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ExceptionEnum;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentException;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImp implements StudentRepository {

    private static final String ID_PREFIX = "S";
    private static final String YEAR = "2020";
    private static final String SUBJECT_CODE = "212";

    private static List<StudentEntity> studentEntities;

    static {
        studentEntities = new LinkedList<>();
    }

    public static List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        studentEntity.setId(generateId());
        try {
            studentEntities.add(studentEntity);
            return studentEntity;
        } catch (Exception ex) {
            throw new StudentException(ExceptionEnum.SAVE_STUDENT_EXCEPTION);
        }
    }

    @Override
    public StudentEntity deleteStudent(String id) {
        Optional<StudentEntity> student = getStudentByIdCommon(id);
        if (student.isPresent()) {
            studentEntities.remove(student.get());
            return student.get();
        }
        throw new StudentException(ExceptionEnum.DELETE_STUDENT_NOT_EXIST);
    }

    @Override
    public List<StudentEntity> getStudents(Optional<Gender> gender) {
        return gender.map(value -> studentEntities.stream()
                .filter(s -> s.getGender() == value)
                .collect(Collectors.toList())).orElseGet(() -> studentEntities);
    }

    @Override
    public StudentEntity getStudentById(String id) {
        Optional<StudentEntity> student = getStudentByIdCommon(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new StudentException(ExceptionEnum.GET_STUDENT_NOT_EXIST);
    }

    @Override
    public StudentEntity updateStudentInfo(StudentEntity studentEntity, String id) {
        Optional<StudentEntity> student = getStudentByIdCommon(id);
        if (student.isPresent()) {
            StudentEntity studentBeEdited = student.get();
            String name = studentEntity.getName() == null ?
                    studentBeEdited.getName() : studentEntity.getName();
            Gender gender = studentEntity.getGender() == null ?
                    studentBeEdited.getGender() : studentEntity.getGender();
            String note = studentEntity.getNote() == null ?
                    studentBeEdited.getNote() : studentEntity.getNote();
            studentBeEdited.setName(name);
            studentBeEdited.setGender(gender);
            studentBeEdited.setNote(note);
            return studentBeEdited;
        }
        throw new StudentException(ExceptionEnum.UPDATE_STUDENT_NOT_EXIST);
    }

    private Optional<StudentEntity> getStudentByIdCommon(String id) {
        return studentEntities.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    private synchronized String generateId() {
        return ID_PREFIX + YEAR + SUBJECT_CODE + String.format("%03d", studentEntities.size() + 1);
    }
}
