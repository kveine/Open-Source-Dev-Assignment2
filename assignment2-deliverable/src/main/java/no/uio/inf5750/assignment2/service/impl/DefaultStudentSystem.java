package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;

import no.uio.inf5750.assignment2.dao.hibernate.HibernateCourseDao;
import no.uio.inf5750.assignment2.dao.hibernate.HibernateStudentDao;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.service.StudentSystem;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;

public class DefaultStudentSystem 
	implements StudentSystem{
	
	@Autowired
	private HibernateCourseDao courseDao;
	
	@Autowired 
	private HibernateStudentDao studentDao;

	@Override
	public int addCourse(String courseCode, String name) {
		Course course = new Course(courseCode, name);
		courseDao.saveCourse(course);
		return course.getId();
	}

	@Override
	public void updateCourse(int courseId, String courseCode, String name) {
		Course course = courseDao.getCourse(courseId);
		course.setCourseCode(courseCode);
		course.setName(name);
		courseDao.saveCourse(course);		
	}

	@Override
	public Course getCourse(int courseId) {
		return courseDao.getCourse(courseId);
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		return courseDao.getCourseByCourseCode(courseCode);
	}

	@Override
	public Course getCourseByName(String name) {
		return courseDao.getCourseByName(name);
	}

	@Override
	public Collection<Course> getAllCourses() {
		return courseDao.getAllCourses();
	}

	@Override
	public void delCourse(int courseId) {
		courseDao.delCourse(courseDao.getCourse(courseId));
		
	}

	@Override
	public void addAttendantToCourse(int courseId, int studentId) {
		Course course = courseDao.getCourse(courseId); 
		Set<Student> attendants = course.getAttendants();
		attendants.add(studentDao.getStudent(studentId));
		course.setAttendants(attendants);
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		Course course = courseDao.getCourse(courseId);
		Set<Student> attendants = course.getAttendants();
		attendants.remove(studentDao.getStudent(studentId));
		course.setAttendants(attendants);
		
	}

	@Override
	public int addStudent(String name) {
		Student student = new Student(name);
		studentDao.saveStudent(student);
		return student.getId();
	}

	@Override
	public void updateStudent(int studentId, String name) {
		Student student = studentDao.getStudent(studentId);
		student.setName(name);
		studentDao.saveStudent(student);
	}

	@Override
	public Student getStudent(int studentId) {
		return studentDao.getStudent(studentId);
	}

	@Override
	public Student getStudentByName(String name) {
		return studentDao.getStudentByName(name);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public void delStudent(int studentId) {
		studentDao.delStudent(studentDao.getStudent(studentId));
		
	}

}
