package com.spring.orm.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {

	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int insertStudent(Student student) {
		Integer result = (Integer) hibernateTemplate.save(student);
		return result;
	}

	@Transactional
	public void updateStudent(Student student) {
		hibernateTemplate.update(student);

	}

	@Transactional
	public void deleteStudent(Student student) {
		hibernateTemplate.delete(student);
	}

	public Student getStudentById(int id) {
		Student student = hibernateTemplate.get(Student.class, id);
		return student;
	}

	public List<Student> getAllStudents() {

		return hibernateTemplate.loadAll(Student.class);

	}

	@Transactional
	public void saveAllStudents(List<Student> students) {
		for (Student student : students) {
			hibernateTemplate.saveOrUpdate(student);
		}

	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
