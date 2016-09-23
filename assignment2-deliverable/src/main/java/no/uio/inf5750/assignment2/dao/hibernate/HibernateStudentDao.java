package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;

@Transactional
public class HibernateStudentDao 
	implements StudentDAO{
	
	static Logger logger = Logger.getLogger(HibernateStudentDao.class);
	@Autowired
	private SessionFactory sessionFactory;

    public void setSessionFactory( SessionFactory sessionFactory )
    {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public int saveStudent(Student student) {
		return (Integer) sessionFactory.getCurrentSession().save( student );
	}

	@Override
	public Student getStudent(int id) {
		return (Student) sessionFactory.getCurrentSession().get( Student.class, id );
	}

	@Override
	public Student getStudentByName(String name) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, name);
	}

	@Override
	public Collection<Student> getAllStudents() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
		List<Student> students = criteria.list();
		return students;
		
	}

	@Override
	public void delStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);
		
	}

}
