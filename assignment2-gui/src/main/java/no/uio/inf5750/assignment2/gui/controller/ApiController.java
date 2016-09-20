package no.uio.inf5750.assignment2.gui.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.uio.inf5750.assignment2.model.Student;
import no.uio.inf5750.assignment2.model.Course;
import no.uio.inf5750.assignment2.service.StudentSystem;

@Controller
public class ApiController {
	
	@Autowired
	private StudentSystem studentSystem;
	
	@RequestMapping(value = "api/student", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Student> listStudents(HttpServletRequest request, HttpServletResponse response) { 
		return studentSystem.getAllStudents();
	}
	
	@RequestMapping(value = "api/student/{student}/location", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Student> setLocation(@PathVariable String student, @RequestParam("longitude") String longitude, 
								@RequestParam("latitude") String latitude, HttpServletRequest request,
								HttpServletResponse response) {
		Student s = studentSystem.getStudentByName(student);
		s.setLatitude(latitude);
		s.setLongitude(longitude);
		
		return studentSystem.getAllStudents();
	}
	
	@RequestMapping(value = "api/course", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Course> listCourses(HttpServletRequest request, HttpServletResponse response) {
		return studentSystem.getAllCourses();
	}
	
	

}
