package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@GetMapping(value = "/student1")
	public StudentDTO test1() {
		StudentDTO studentDto = new StudentDTO();
		studentDto.setName("yuna");
		studentDto.setAge(20);
		studentDto.setJavaGrade("A+");
		studentDto.setOracleGrade("C");
		
		System.out.println("객체 정보:" + studentDto.toString());
		
		return studentDto;
	}
	
	@GetMapping(value = "/student2")
	public StudentDTO test2() {
		StudentDTO studentDto = new StudentDTO();
		studentDto.setName("jimin");
		studentDto.setAge(21);
		studentDto.setJavaGrade("B+");
		studentDto.setOracleGrade("F");
		
		System.out.println("객체 정보:" + studentDto.toString());
		
		return studentDto;
	}
	
}
