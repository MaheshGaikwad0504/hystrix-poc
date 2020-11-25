package com.hystrix.hystrixdemo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.hystrix.hystrixdemo.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Repository
public class StudentRepository {

	@Autowired
	RestTemplate restTemplate;

	private static List<Student> studentList = Arrays.asList(new Student(104, "Rahul", "Baramati", 80),
			new Student(101, "Mahesh", "Solapur", 87), new Student(103, "Ganesh", "Pune", 90),
			new Student(102, "Ramesh", "Indapur", 67));

	public Student getStudent(int id) {
		for (Student s : studentList) {
			if (s.getStudentId() == id) {
				Student student = new Student(s.getStudentId(), s.getName(), s.getAddress(), s.getMarks());
				return student;
			}
		}
		return null;
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public List<Student> getStudentsFromStudentService() {

		ResponseEntity<List<Student>> resultist = restTemplate.exchange("http://localhost:8098/students/test",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
				});
		List<Student> students = resultist.getBody();
		return students;
	}

	public List<Student> fallback() {
		System.out.println("Fallback called!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return new ArrayList<Student>();
	}
}
