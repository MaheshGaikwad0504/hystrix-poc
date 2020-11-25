package com.hystrix.hystrixdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hystrix.hystrixdemo.model.Student;
import com.hystrix.hystrixdemo.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	public Student getStudent(int id) {
		return repository.getStudent(id);
	}

	public List<Student> getStudentsFromStudentService() {
		return repository.getStudentsFromStudentService();
	}

}
