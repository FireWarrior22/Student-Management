package com.spring.practice.Student.Management.DAOs;

import com.spring.practice.Student.Management.Model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentDAO {

    public List<Student> getAllStudents();
    public Optional<Student> getStudentByID(int id);
    public List<Student> getStudentByAge(int age);
    public List<Student> getStudentByName(String name);
    public Student createStudent(Student student);
    public Optional<Student> updateStudent(int id, Student student);
    public boolean deleteStudent(int id);


}
