package no.uio.inf5750.assignment2.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		int courseId = studentSystem.addCourse("INF1234","test");
		Course course = studentSystem.getCourseByCourseCode("INF1234");
		assertEquals(courseId, course.getId());
	}

	@Test
	public void getCourseByNameTest() {
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
		int courseId = studentSystem.addCourse("INF4820","test");
		Course course = studentSystem.getCourse(courseId);
	
		int studentId = studentSystem.addStudent("Kristin");
		Student student = studentSystem.getStudent(studentId);
	
		course.getAttendants().add(student);
		student.getCourses().add(course);
	
		hibernateStudentDao.saveStudent(student);
	
		assertEquals(true, course.getAttendants().equals(student));
		assertEquals(true, student.getCourses().equals(course));
	}

	@Test
	public void removeAttendantFromCourseTest() {
		int studentId = studentSystem.addStudent("Kristin");
		Student student = studentSystem.getStudent(studentId);
	
		int courseId = studentSystem.addCourse("INF4820","test");
		Course course = studentSystem.getCourse(courseId);
	
		course.getAttendants().remove(student);
		student.getCourses().remove(course);
		hibernateCourseDao.saveCourse(course);
		hibernateStudentDao.saveStudent(student);
	
		Student student2 = studentSystem.getStudent(studentId);
		Course course2 = studentSystem.getCourse(courseId);
	
		assertNotEquals(course.getAttendants(), course2.getAttendants());
		assertEquals(student.getCourses(), student2.getCourses());
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
