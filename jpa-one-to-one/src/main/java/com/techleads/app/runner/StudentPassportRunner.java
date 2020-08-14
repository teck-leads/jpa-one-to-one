package com.techleads.app.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.techleads.app.model.Passport;
import com.techleads.app.model.Student;
import com.techleads.app.repository.StudentRepository;

@Component
public class StudentPassportRunner implements CommandLineRunner {
	
	Logger logger=LoggerFactory.getLogger(StudentPassportRunner.class);

	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception {
	
		Student student = studentRepository.findById(300);
		logger.info("Student details {} "+student);
		logger.info("Student passport details {} "+student.getPassport());
		
		Student student1=new Student("aman");
		Passport passport=new Passport("BHR123");
		studentRepository.saveStudentPassport(student1, passport);
		
		
	Passport findPassportById = studentRepository.findPassportById(350);
		
	logger.info("passport details {} "+findPassportById);
	logger.info("Passport Student details {} "+findPassportById.getStudent());
	
	studentRepository.updateStudentPassport(302);
		
		
	}

}
