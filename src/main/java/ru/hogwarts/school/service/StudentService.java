package ru.hogwarts.school.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentQuerySQL;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

@Service
public class StudentService {

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }


    public Student addStudent(Student student) {
        logger.info("Was invoked method for add student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for get student");
        return studentRepository.findById(id).get();
    }

    public Faculty findFacultyByStudent(long id) {
        logger.info("Was invoked method for get faculty by student");
        return studentRepository.findById(id).get().getFaculty();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for get students by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for get students between age");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Collection<Student> findByFaculty(long faculty) {
        logger.info("Was invoked method for get students by faculty");
        return studentRepository.findStudentByFaculty_Id(faculty);
    }

    public List<StudentQuerySQL> getNameAndAge() {
        logger.info("Was invoked method for get last five students");
        return studentRepository.getNameAndAgeStudents();
    }

    public Integer getCountStudents() {
        logger.info("Was invoked method for get count students");
        return studentRepository.getCountAllStudents();
    }

    public Double getAverageAge() {
        logger.info("Was invoked method for get average age students");
        return studentRepository.getAverageAge();
    }

}