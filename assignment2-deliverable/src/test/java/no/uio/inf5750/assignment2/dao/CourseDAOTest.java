package no.uio.inf5750.assignment2.dao;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import no.uio.inf5750.assignment2.dao.hibernate.HibernateCourseDao;
import no.uio.inf5750.assignment2.model.Course;

public class CourseDAOTest {
	/*HibernateCourseDao courseDao;
	Course course ;
	
	@Before
	public void before(){
		courseDao = new HibernateCourseDao();
		course = new Course("INF5750", "Open Source Development");
	}

	@Test
	public void saveCourseTest(){
		int id = courseDao.saveCourse(course);
		course = courseDao.getCourse(id);
		assertEquals(id, course.getId());
		assertEquals(course.getCourseCode(), "INF5750");
		assertEquals(course.getName(), "Open Source Development");
	}
	
	@Test
	public void getCourseTest(){
		Course c = courseDao.getCourse(course.getId());
		assertNotNull(c);
		course = courseDao.getCourse(c.getId());
		assertEquals(c, course.getId());
	}
	
	@Test
	public void getCourseByCourseCodeTest(){
		Course c = courseDao.getCourseByCourseCode(course.getCourseCode());
		assertNotNull(c);
	}
	
	@Test
	public void getAllCoursesTest(){
		Collection<Course> c = courseDao.getAllCourses();
		assertNotNull(c);
	}
	
	@Test
	public void delCourseTest(){
		courseDao.delCourse(course);
	}*/

}
