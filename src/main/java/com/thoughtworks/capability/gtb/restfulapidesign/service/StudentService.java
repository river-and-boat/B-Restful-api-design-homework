package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity saveStudent(StudentEntity student) {
        return studentRepository.saveStudent(student);
    }

    public StudentEntity deleteStudent(String id) {
        return studentRepository.deleteStudent(id);
    }

    public List<StudentEntity> getStudents(Optional<Gender> gender) {
        return studentRepository.getStudents(gender);
    }

    public StudentEntity getStudentById(String id) {
        return studentRepository.getStudentById(id);
    }

    public StudentEntity updateStudent(StudentEntity student, String id) {
        return studentRepository.updateStudentInfo(student, id);
    }
}
