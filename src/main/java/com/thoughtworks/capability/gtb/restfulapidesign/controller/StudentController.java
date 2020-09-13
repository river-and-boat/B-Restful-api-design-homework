package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentEntity saveStudent(@RequestBody @Valid StudentEntity studentEntity) {
        return studentService.saveStudent(studentEntity);
    }

    @DeleteMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StudentEntity deleteStudent(@PathVariable(value = "id") String id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping(value = "/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentEntity> getStudents(@RequestParam Optional<Gender> gender) {
        return studentService.getStudents(gender);
    }

    @GetMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentEntity getStudentById(@PathVariable(value = "id") String id) {
        return studentService.getStudentById(id);
    }

    @PutMapping(value = "/students/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentEntity updateStudentInfo(@PathVariable(value = "id") String id,
                                           @RequestBody StudentEntity studentEntity) {
        return studentService.updateStudent(studentEntity, id);
    }
}
