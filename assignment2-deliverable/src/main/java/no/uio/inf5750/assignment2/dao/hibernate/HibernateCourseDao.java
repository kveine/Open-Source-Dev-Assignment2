package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;

import org.hibernate.SessionFactory;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;

public class HibernateCourseDao 
	implements CourseDAO{
	private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveCourse(Course course) {
		return (Integer) sessionFactory.getCurrentSession().save( course );
	}

	@Override
	public Course getCourse(int id) {
		return (Course) sessionFactory.getCurrentSession().get(Course.class, id);
	}

	@Override
	public Course getCourseByCourseCode(String courseCode) {
		return (Course) sessionFactory.getCurrentSession().get(Course.class, courseCode);
	}

	@Override
	public Course getCourseByName(String name) {
		return (Course) sessionFactory.getCurrentSession().get(Course.class, name);
	}

	@Override
	public Collection<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
		
	}

}