package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.IStudentRepo;
import com.example.demo.repositories.StudentRepo;

import java.util.Collections;
import java.util.List;

public class StudentService implements IStudentService{
    private static IStudentRepo studentRepo = new StudentRepo();

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public Boolean deleteById(Long id) {
        return studentRepo.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public void update(Student student) {
        studentRepo.update(student);
    }

    @Override
    public List<Student> searchByName(String search) {
        return studentRepo.searchByName(search);
    }
}
