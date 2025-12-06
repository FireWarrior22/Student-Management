package com.spring.practice.Student.Management.DAOs;
import com.spring.practice.Student.Management.Model.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class StudentDAOImpl implements StudentDAO{

    private final ConcurrentMap<Integer,Student> map = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Student> getStudentByID(int id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Student> getStudentByAge(int age) {
       return map.values().stream().filter(student -> student.getAge()==age).toList();
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return map.values().stream().filter(student -> student.getStudentName().toLowerCase().startsWith(name.toLowerCase())).toList();
    }

    @Override
    public Student createStudent(Student student) {
        int id = idGen.getAndIncrement();
        student.setId(id);
        map.put(id, student);
        return student;
    }

    @Override
    public Optional<Student> updateStudent(int id, Student student) {

        if(map.get(id)!=null){
            student.setId(id);
            map.put(id,student);
            return Optional.of(student);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteStudent(int id) {
            return map.remove(id) != null;
    }
}
