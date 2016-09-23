package no.uio.inf5750.assignment2.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.service.StudentSystem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
public class DefaultStudentSystem 
	implements StudentSystem{
	
	static Logger logger = Logger.getLogger(DefaultStudentSystem.class);
	@Autowired
	private CourseDAO courseDao;
	
	@Autowired 
	private StudentDAO studentDao;

	@Override
	public int addCourse(String courseCode, String name) {
		Course course = new Course(courseCode, name);
		return courseDao.saveCourse(course);
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
		Student student = studentDao.getStudent(studentId);
		course.getAttendants().add(student);
		student.getCourses().add(course);
		studentDao.saveStudent(student);
	}

	@Override
	public void removeAttendantFromCourse(int courseId, int studentId) {
		Course course = courseDao.getCourse(courseId); 
		Student student = studentDao.getStudent(studentId);
		course.getAttendants().remove(student);
		student.getCourses().remove(course);
		studentDao.saveStudent(student);
	}

	@Override
	public int addStudent(String name) {
		Student student = new Student(name);
		return studentDao.saveStudent(student);
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
		Student student = studentDao.getStudent(studentId);
		Set<Course> courses = student.getCourses();
		for(int i = 0; i < courses.size(); i++){
			removeAttendantFromCourse(courses.iterator().next().getId(), student.getId());
		}
		studentDao.delStudent(studentDao.getStudent(studentId));
		
	}

	@Override
	public void setStudentLocation(int studentId, String latitude, String longitude) {
		Student student =studentDao.getStudent(studentId);
		student.setLatitude(latitude);
		student.setLongitude(longitude);
		studentDao.saveStudent(student);
		
		
	}

}
