package no.uio.inf5750.assignment2.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/META-INF/assignment2/beans.xml"})
@Transactional
public class StudentSystemTest {
	/*
	 * 
	 * Noen av testene får error og noen får failure,
	 * jeg har kommentert mer rundt de forskjellige testene som ikke fungerer i metodene.
	 * Jeg håper jeg får muligheten til å rette på dette etter hjelp og tilbakemeldinger av dere, 
	 * og levere på nytt så jeg kan få betsått :)
	 * 
	 * 
	 */
	@Autowired
	private StudentSystem studentSystem;

	@Autowired
	private CourseDAO hibernateCourseDao;
	@Autowired
	private StudentDAO hibernateStudentDao;

	@Test
	public void addCourseTest() {
		int courseId = studentSystem.addCourse("INF1234","test");
		Course course = studentSystem.getCourse(courseId);
		assertEquals(courseId, course.getId());
	}

	@Test
	public void updateCourseTest() {
		int courseId = studentSystem.addCourse("INF1234","test");
		studentSystem.updateCourse(courseId, "INF5678", "INF5678");
		Course course = studentSystem.getCourse(courseId);
	
		assertEquals("INF5678", course.getCourseCode());
		assertEquals("INF5678", course.getName());
	}

	@Test
	public void getCourseTest() {
		int courseId = studentSystem.addCourse("INF1234","test");
		Course course = studentSystem.getCourse(courseId);
		assertEquals(courseId, course.getId());
	}

	@Test
	public void getCourseByCourseCodeTest() {
		/*
		 * Her får jeg error i test:
		 * getCourseByCourseCodeTest(no.uio.inf5750.assignment2.service.StudentSystemTest): 
		 * Provided id of the wrong type for class no.uio.inf5750.assignment2.model.Course. 
		 * Expected: class java.lang.Integer, got class java.lang.String
		 * Forstår ikke feilmeldingen da jeg mener at begge variablene er integer
		 * 
		 */
		int courseId = studentSystem.addCourse("INF1234","test");
		Course course = studentSystem.getCourseByCourseCode("INF1234");
		assertEquals(courseId, course.getId());
	}

	@Test
	public void getCourseByNameTest() {
		/* 
		 * Her får jeg error i test:
		 * Provided id of the wrong type for class no.uio.inf5750.assignment2.model.Course. 
		 * Expected: class java.lang.Integer, got class java.lang.String
		 * Forstår ikke feilmeldingen da jeg mener at begge variablene er integer
		 * 
		 * */
		int courseId = studentSystem.addCourse("INF1234","test");
		Course course = studentSystem.getCourseByName("test");
		assertEquals(courseId, course.getId());
	}

	@Test
	public void getAllCoursesTest() {
		int courseId1 = studentSystem.addCourse("INF1234","test");
		int courseId2 = studentSystem.addCourse("INF5678","test2");
		int courseId3 = studentSystem.addCourse("INF9123","test3");
	
		List<Integer> courseIds = new ArrayList<Integer>();
	
		courseIds.add(courseId1);
		courseIds.add(courseId2);
		courseIds.add(courseId3);
	
		java.util.Collection<Course> courses = studentSystem.getAllCourses();
	
		for(Course c : courses)
		{
			if(c.getId() == courseId1)
			{
				assertTrue(true);
			}
			else if(c.getId() == courseId2)
			{
				assertTrue(true);
			}
			else if(c.getId() == courseId3)
			{
				assertTrue(true);
			}
			else {
				fail("Test failed");
			}
		}

	}

	@Test
	public void delCourseTest() {
		int courseId = studentSystem.addCourse("INF4490","test");
		studentSystem.delCourse(courseId);
		Course course = studentSystem.getCourse(courseId);
		assertEquals(null, course);
	}

	@Test
	public void addAttendantToCourseTest() {
		/*
		 * 
		 * Her får jeg failure i test:
		 * addAttendantToCourseTest(no.uio.inf5750.assignment2.service.StudentSystemTest): expected:<true> but was:<false
		 * Jeg sliter litt med hvordan jeg skal teste på lista
		 * 
		 */
		int courseId = studentSystem.addCourse("INF4490", "test");
		int studentId = studentSystem.addStudent("Kristin");
		studentSystem.addAttendantToCourse(courseId, studentId);
		Course course = studentSystem.getCourse(courseId);
		Student student = studentSystem.getStudent(studentId);
		
		assertEquals(true, course.getAttendants().equals(student));
		assertEquals(true, student.getCourses().equals(course));
	}

	@Test
	public void removeAttendantFromCourseTest() {
		int courseId = studentSystem.addCourse("INF4490", "test");
		int studentId = studentSystem.addStudent("Kristin");
		studentSystem.removeAttendantFromCourse(courseId, studentId);
		Course course = studentSystem.getCourse(courseId);
		Student student = studentSystem.getStudent(studentId);
	
		assertEquals(false, course.getAttendants().equals(student));
		assertEquals(false, student.getCourses().equals(course));
	}

	@Test
	public void addStudentTest() {

		int studentId = studentSystem.addStudent("Kristin");
		Student student = studentSystem.getStudent(studentId);
		assertEquals(studentId, student.getId());
	}

	@Test
	public void updateStudentTest() {
		int studentId = studentSystem.addStudent("Kristin");
		studentSystem.updateStudent(studentId, "Kristin Kveine");
		Student student = studentSystem.getStudent(studentId);
	
		assertEquals("Kristin Kveine", student.getName());
	}

	@Test
	public void getStudentTest() {
		int studentId = studentSystem.addStudent("Kristin");
		Student student = studentSystem.getStudent(studentId);
		assertEquals(studentId, student.getId());
	}

	@Test
	public void getStudentByNameTest() {
		/*
		 * Her får jeg error i test:
		 * getStudentByNameTest(no.uio.inf5750.assignment2.service.StudentSystemTest): 
		 * Provided id of the wrong type for class no.uio.inf5750.assignment2.model.Student. 
		 * Expected: class java.lang.Integer, got class java.lang.String
		 * Forstår ikke feilmeldingen da jeg mener at begge variablene er int
		 * 
		 */
		int studentId = studentSystem.addStudent("Kristin");
		Student student = studentSystem.getStudentByName("Kristin");
		assertEquals(studentId, student.getId());
	}

	@Test
	public void getAllStudentsTest() {
		int studentId1 = studentSystem.addStudent("Kristin");
		int studentId2 = studentSystem.addStudent("Henrik");
		int studentId3 = studentSystem.addStudent("Vegard");
	
		List<Integer> studentIds = new ArrayList<Integer>();
	
		studentIds.add(studentId1);
		studentIds.add(studentId2);
		studentIds.add(studentId3);
	
		Collection<Student> students = studentSystem.getAllStudents();
	
		for(Student s : students)
		{
			if(s.getId() == studentId1)
			{
				assertTrue(true);
			}
			else if(s.getId() == studentId2)
			{
				assertTrue(true);
			}
			else if(s.getId() == studentId3)
			{
				assertTrue(true);
			}
			else {
				fail("Test failed");
			}
		}
	}

	@Test
	public void delStudentTest() {
		int studentId = studentSystem.addStudent("Kristin");
		studentSystem.delStudent(studentId);
		Student student = studentSystem.getStudent(studentId);
		assertEquals(null, student);
	}

	@Test
	public void setStudentLocationTest() {
		int studentId = studentSystem.addStudent("Kristin");
		String latitude = "3890";
		String longitude = "6254";
	
		Student student = studentSystem.getStudent(studentId);
		student.setLatitude(latitude);
		student.setLongitude(longitude);
		hibernateStudentDao.saveStudent(student);
	
		student = studentSystem.getStudent(studentId);
	
		assertEquals(latitude, student.getLatitude());
		assertEquals(longitude, student.getLongitude());
	}
}
