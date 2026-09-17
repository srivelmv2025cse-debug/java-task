package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final ConcurrentHashMap<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Student> getAllStudents() {
        return students.values().stream().toList();
    }

    public Student getStudentById(Long id) {
        return students.get(id);
    }

    public Student createStudent(Student student) {
        long id = idGenerator.incrementAndGet();
        student.setId(id);
        students.put(id, student);
        return student;
    }
}
