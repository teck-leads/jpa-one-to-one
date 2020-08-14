package com.techleads.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.techleads.app.model.Passport;
import com.techleads.app.model.Student;
@Transactional
@Repository
public class StudentRepository {
	@Autowired
	private EntityManager entityManager;
	@Transactional
	public Student findById(Integer id) {
		Student student = entityManager.find(Student.class, id);
		return student;
	}
	
	public void deleteById(Integer id) {
		Student student = findById(id);
		
		entityManager.remove(student);
	}
	public Student inserUpdate(Student student) {
		if(StringUtils.isEmpty(student.getId())) {
			entityManager.persist(student);
		}else {
			entityManager.merge(student);
		}
		return student;
	}
	
	
	public void saveStudentPassport(Student student, Passport passport) {
		entityManager.persist(passport);
		student.setPassport(passport);
		entityManager.persist(student);
		
	}
	public  void playWithEntityManger() {
		Student student = new Student("PersistMethod-Student");
		entityManager.persist(student);
		entityManager.flush();//changes will go the db
		student.setName("PersistMethod-Student-Updated"); //this also will be updated even it is not requested to save
		entityManager.flush();//all the above changes will go the db and save
		entityManager.refresh(student);
		//entityManager.clear();//clear everything and don't track anything
		student.setName("PersistMethod-Student-do not track this change: detach");
		entityManager.detach(student);
		
	}
	
	public List<Student> findAll(){
		TypedQuery<Student> createNamedQuery = entityManager.createNamedQuery("FIND_ALL_COURSES", Student.class);
		List<Student> courses = createNamedQuery.getResultList();
		return courses;
	}
	
	public Student saveStudentWithPassport(Passport passport, Student student) {
		entityManager.persist(passport);
		student.setPassport(passport);
		entityManager.persist(student);
		return student;
	}
	
	public Passport findPassportById(Integer id) {
		Passport passport = entityManager.find(Passport.class, id);
		return passport;
	}
	
	
	public void updateStudentPassport(Integer id) {
		Student stud = entityManager.find(Student.class, id);
		Passport pssprt = stud.getPassport();
		
		pssprt.setNumber("Updated_IND123");
		stud.setName("Updated_Kumar");
		
	}

}
