package eu.ensup.service;

import java.util.List;

import eu.ensup.domaine.Course;
import eu.ensup.domaine.Student;

public interface ICourseService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ensup.jpaGestionEnsup.service.ICourseService#associateCourse(java.lang.
	 * String, int)
	 */
	void associateCourse(Course course, Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.ICourseService#getAllCourses()
	 */
	List<Course> getAllCourses();

	void createCourse(Course course);
	
	Course getCourse(String theme);
	
	void updateCourse(String theme, Course course);
	
	void deleteCourse(String theme);
}