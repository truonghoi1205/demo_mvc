package com.example.demo.repositories;

import com.example.demo.models.Student;

import java.util.List;

public interface IStudentRepo {
    List<Student> findAll();

    void save(Student student);

    Boolean deleteById(Long id);
    Student findById(Long id);
    void update(Student student);

    List<Student> searchByName(String search);
}
