package com.spring.practice.Student.Management.Controller;
import com.spring.practice.Student.Management.DAOs.StudentDAO;
import com.spring.practice.Student.Management.Model.Student;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentDAO.getAllStudents());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable int id){
        return studentDAO.getStudentByID(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<Student>> getStudentByAge(@PathVariable int age){
        return ResponseEntity.ok(studentDAO.getStudentByAge(age));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name){
        return ResponseEntity.ok(studentDAO.getStudentByName(name));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
        Student created = studentDAO.createStudent(student);
        URI location = URI.create("/students/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        return studentDAO.updateStudent(id, student).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id){
        boolean check = studentDAO.deleteStudent(id);

        if(check)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

}
