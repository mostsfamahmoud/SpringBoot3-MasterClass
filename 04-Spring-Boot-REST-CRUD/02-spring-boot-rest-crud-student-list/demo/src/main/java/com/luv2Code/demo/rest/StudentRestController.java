package com.luv2Code.demo.rest;

import com.luv2Code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students = null;

    // Define @PostConstruct to load the student Data Only Once.
    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        students.add(
                new Student("Mostafa","Mahmoud")
        );

        students.add(
                new Student("Mario","Rosi")
        );

        students.add(
                new Student("Mary","Smith")
        );
    }

    // Define an endpoint for "/students" --> Return a list of students
    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;
    }

}
