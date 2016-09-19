package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.CourseDAO;
import no.uio.inf5750.assignment2.model.Course;

@Transactional
public class HibernateCourseDao 
	implements CourseDAO{
	
	static Logger logger = Logger.getLogger(HibernateCourseDao.class);
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
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Course.class);
		List<Course> courses = criteria.list();
		return courses;
	}

	@Override
	public void delCourse(Course course) {
		sessionFactory.getCurrentSession().delete(course);
		
	}

}
