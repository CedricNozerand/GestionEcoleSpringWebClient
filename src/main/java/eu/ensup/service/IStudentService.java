package eu.ensup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.ensup.domaine.Student;

@Service
public interface IStudentService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IStudentService#createStudent(eu.ensup.
	 * jpaGestionEnsup.domaine.Student)
	 */
	void createStudent(Student student);

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IStudentService#getStudent(int)
	 */
	Student getStudent(Long id);

	Object getStudentSearch(Long id);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.ensup.jpaGestionEnsup.service.IStudentService#getStudentByMail(java.lang.
	 * String)
	 */
	Student getStudentByMail(String mail);

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IStudentService#getAllStudents()
	 */
	List<Student> getAllStudents();

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IStudentService#deleteStudent(int)
	 */
	void deleteStudent(Long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IStudentService#updateStudent(eu.ensup.
	 * jpaGestionEnsup.domaine.Student)
	 */
	void updateStudent(Long id, Student student);

	/**
	 * Recherche un ou plusieurs étudiants en fonction de leur données.
	 * 
	 * @param firstName Le prénom du ou des étudiants à chercher.
	 * @param lastName  Le nom de famille du ou des étudiants à chercher.
	 * @return La liste des étudiants correspondant au prénom et au nom entrés.
	 */
	List<Student> searchStudent(String firstName, String lastName);

}