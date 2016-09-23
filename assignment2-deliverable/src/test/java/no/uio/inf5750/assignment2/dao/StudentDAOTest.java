package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import no.uio.inf5750.assignment2.dao.hibernate.HibernateStudentDao;
import no.uio.inf5750.assignment2.model.Student;

public class StudentDAOTest {
	
	/*HibernateStudentDao studentDao;
	Student student;
	
	@Before
	public void before(){
		studentDao = new HibernateStudentDao();
		student = new Student("Kristin Kveine");
	}
	
	@Test
	public void saveStudentTest(){
		int id = studentDao.saveStudent(student);
		student = studentDao.getStudent(id);
		assertEquals(id, student.getId());
		assertEquals(student.getName(), "Kristin Kveine");
	}
	
	@Test
	public void getStudentTest(){
		Student s = studentDao.getStudent(student.getId());
		assertNotNull(s);
		
	}
	
	@Test
	public void getStudentByNameTest(){
		studentDao.getStudentByName(student.getName());
	}
	
	@Test
	public void getAllStudentTest(){
		studentDao.getAllStudents();
	}
	
	@Test
	public void delStudentTest(){
		studentDao.delStudent(student);
	}*/
}
