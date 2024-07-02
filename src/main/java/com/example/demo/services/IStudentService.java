package com.example.demo.services;

import com.example.demo.models.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    Boolean deleteById(Long id);
    Student findById(Long id);
    void update(Student student);

    List<Student> searchByName(String search);
}
