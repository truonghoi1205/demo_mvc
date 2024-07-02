package com.example.demo.repositories;

import com.example.demo.models.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentRepo implements IStudentRepo{
    private static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student(1L, "haiTT", "QN", 10.0f));
        students.add(new Student(2L, "Bảo Ngọc", "HN", 8.0f));
        students.add(new Student(3L, "Bảo Kỳ", "DN", 6.0f));
        students.add(new Student(5L, "Cook", "Bàn ăn", 2f));
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public void save(Student student) {
        student.setId(students.get(students.size() - 1).getId() + 1);
        students.add(student);
    }

    @Override
    public Boolean deleteById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    @Override
    public Student findById(Long id) {
       for (Student student : students) {
           if(student.getId() == id) {
               return student;
           }
       }
        return null;
    }

    @Override
    public void update(Student student) {
      Student student1 =  findById(student.getId());
      student1.setName(student.getName());
      student1.setAddress(student.getAddress());
      student1.setPoint(student.getPoint());
    }

    @Override
    public List<Student> searchByName(String search) {
        List<Student> studentsa = new ArrayList<>();
        for (Student student : students) {
            if(student.getName().indexOf(search) != -1) {
                studentsa.add(student);
            }
        }
        return studentsa;
    }
}
