package com.hystrix.hystrixdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hystrix.hystrixdemo.model.Student;
import com.hystrix.hystrixdemo.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable int id) {
		return service.getStudent(id);
	}

	@GetMapping("/all")
	public List<Student> getStudentsFromStudentService() {
		return service.getStudentsFromStudentService();
	}
}
