package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.services.IStudentService;
import com.example.demo.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private static IStudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                req.getRequestDispatcher("/student/create.jsp").forward(req, resp);
                break;
            case "update":
                Long id = Long.parseLong(req.getParameter("id"));
                Student student = studentService.findById(id);
                req.setAttribute("student",student);
                req.getRequestDispatcher("/student/update.jsp").forward(req, resp);
                break;
            case "search":
                String search = req.getParameter("search");
                List<Student> students1 = studentService.searchByName(search);
                req.setAttribute("students",students1);
                req.getRequestDispatcher("/student/list.jsp").forward(req,resp);
                break;
            default:
                List<Student> students = studentService.findAll();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        Long id;
        String name = null;
        String address = null;
        Float points = null;
        Student student;
        if (action.equals("create") || action.equals("update")) {
            name = req.getParameter("name");
            address = req.getParameter("address");
            points = Float.parseFloat(req.getParameter("point"));

        }
        switch (action) {
            case "create":
                student = new Student(name, address, points);
                studentService.save(student);
                resp.sendRedirect("/student");
                break;
            case "delete":
                id = Long.parseLong(req.getParameter("id"));
                Boolean isDelete = studentService.deleteById(id);
                if (isDelete) {
                    resp.sendRedirect("/student");
                } else {
                    req.setAttribute("message", "Xóa không thành công");
                    List<Student> students = studentService.findAll();
                    req.setAttribute("students", students);
                    req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                }
                break;
            case "update":
                id = Long.parseLong(req.getParameter("id"));
                student = new Student(id, name, address, points);
                studentService.update(student);
                resp.sendRedirect("/student");
                break;
        }
    }
}
