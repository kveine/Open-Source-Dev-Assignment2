package no.uio.inf5750.assignment2.dao.hibernate;

import java.util.Collection;
import org.hibernate.SessionFactory;
import no.uio.inf5750.assignment2.dao.StudentDAO;
import no.uio.inf5750.assignment2.model.Student;

public class HibernateStudentDao 
	implements StudentDAO{
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);
		
	}

}