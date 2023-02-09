package ru.hogwarts.school.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentQuerySQL;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudentInfo(@PathVariable Long id) {
        Faculty faculty = studentService.findFacultyByStudent(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age,
                                                            @RequestParam(required = false) int min,
                                                            @RequestParam(required = false) int max,
                                                            @RequestParam(required = false) long faculty) {
        if (age != 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        if (min != 0 && max != 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        if (faculty != 0) {
            return ResponseEntity.ok(studentService.findByFaculty(faculty));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/get-last-five-students")
    public List<StudentQuerySQL> getNameAndAgeStudents() {
        return studentService.getNameAndAge();
    }

    @GetMapping("/get-count-all-students")
    public Integer getCountAllStudents() {
        return studentService.getCountStudents();
    }

    @GetMapping("/get-average-age-students")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

}