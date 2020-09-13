package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.entity.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.entity.StudentEntity;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.StudentException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    private StudentEntity studentEntity = null;

    @BeforeEach
    void init() {
        studentEntity = StudentEntity.builder()
                .name("Jyz").gender(Gender.MALE).note("none").build();
    }

    @AfterEach
    void cleanUp() {
        StudentRepository.getStudentEntities().clear();
    }

    @Test
    public void testSaveStudent() {
        StudentEntity studentEntity = studentService.saveStudent(this.studentEntity);
        assertEquals("S2020212001", studentEntity.getId());
    }

    @Test
    public void testDeleteStudentWhenExist() {
        StudentEntity studentEntity = studentService.saveStudent(this.studentEntity);
        StudentEntity deletedEntity = studentService.deleteStudent(studentEntity.getId());
        assertEquals(studentEntity.getId(), deletedEntity.getId());
    }

    @Test
    public void testDeleteStudentWhenNotExist() {
        StudentException studentException = assertThrows(StudentException.class,
                () -> studentService.deleteStudent("2013212755"),
                "Expected doThing() to throw, but it didn't");
        assertEquals("delete student not exist",
                studentException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testGetStudentsWithOutGender() {
        studentService.saveStudent(this.studentEntity);
        StudentEntity studentEntityFemale = StudentEntity.builder().name("zym")
                .note("none").gender(Gender.FEMALE).build();
        studentService.saveStudent(studentEntityFemale);
        StudentEntity studentEntityThird = StudentEntity.builder().name("dky")
                .note("none").gender(Gender.MALE).build();
        studentService.saveStudent(studentEntityThird);

        List<StudentEntity> students = studentService.getStudents(Optional.ofNullable(null));

        assertEquals(3, students.size());
    }

    @Test
    public void testGetStudentByGender() {
        studentService.saveStudent(this.studentEntity);
        StudentEntity studentEntityFemale = StudentEntity.builder().name("zym")
                .note("none").gender(Gender.FEMALE).build();
        studentService.saveStudent(studentEntityFemale);
        StudentEntity studentEntityThird = StudentEntity.builder().name("dky")
                .note("none").gender(Gender.MALE).build();
        studentService.saveStudent(studentEntityThird);

        List<StudentEntity> students = studentService.getStudents(Optional.ofNullable(Gender.MALE));
        assertEquals(2, students.size());
    }

    @Test
    public void testGetStudentByIdWhenExist() {
        StudentEntity studentEntity = studentService.saveStudent(this.studentEntity);
        StudentEntity result = studentService.getStudentById(studentEntity.getId());
        assertEquals(studentEntity.getId(), result.getId());
    }

    @Test
    public void testGetStudentByIdWhenNotExist() {
        StudentException studentException = assertThrows(StudentException.class,
                () -> studentService.getStudentById("2013212755"),
                "Expected doThing() to throw, but it didn't");
        assertEquals("student not exist",
                studentException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testUpdateStudentWhenNotExist() {
        StudentEntity waitToBeUpdatedEntity = StudentEntity.builder()
                .gender(Gender.MALE).name("update").note("update test").build();
        StudentException studentException = assertThrows(StudentException.class,
                () -> studentService.updateStudent(waitToBeUpdatedEntity, "2013212755"),
                "Expected doThing() to throw, but it didn't");
        assertEquals("can not find a student to update",
                studentException.getExceptionEnum().getErrorMessage());
    }

    @Test
    public void testUpdateStudentAllInfo() {
        StudentEntity studentEntity = studentService.saveStudent(this.studentEntity);
        StudentEntity waitToBeUpdatedEntity = StudentEntity.builder()
                .gender(Gender.FEMALE).name("update").note("update test").build();
        StudentEntity updatedStudent = studentService.updateStudent(waitToBeUpdatedEntity, studentEntity.getId());
        assertEquals("update", updatedStudent.getName());
        assertEquals("update test", updatedStudent.getNote());
        assertEquals(Gender.FEMALE, updatedStudent.getGender());
    }

    @Test
    public void testUpdateStudentPartialInfo() {
        StudentEntity studentEntity = studentService.saveStudent(this.studentEntity);
        StudentEntity waitToBeUpdatedEntity = StudentEntity.builder()
                .note("update test").build();
        StudentEntity updatedStudent = studentService.updateStudent(waitToBeUpdatedEntity, studentEntity.getId());
        assertEquals("Jyz", updatedStudent.getName());
        assertEquals("update test", updatedStudent.getNote());
        assertEquals(Gender.MALE, updatedStudent.getGender());
    }
}