package eu.ensup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import eu.ensup.domaine.Course;
import eu.ensup.domaine.ErrorMessage;
import eu.ensup.domaine.Student;
import eu.ensup.service.ICourseService;
import eu.ensup.service.IStudentService;

@Controller
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ICourseService courseService;
	
	
	/********************************
	 ****** Méthodes d'affichages****
	 ********************************/
	
	/**
	 * Méthode qui affiche la page de création d'un étudiant
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/createStudent")
	public String createStudent() {
		return "createStudent";
	}
	
	/**
	 * Méthode qui affiche la page de modification d'un étudiant
	 * @param id: l'id de l'étudiant à modifier
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute( "student", studentService.getStudent(id) );
		return "updateStudent";
	}
	
	/**
	 * Méthode qui affiche le détail d'un étudiant
	 * @param id: l'id de l'étudiant à modifier
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/showStudent/{id}")
	public String showStudent(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute( "student", studentService.getStudent(id) );
		return "showStudent";
	}
	
	/**
	 * Méthode qui affiche la page de recherche d'un étudiant
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/getSearchStudent")
	public String getSearchStudent() {
		return "searchStudent";
	}
	
	/**
	 * Méthode qui affiche la page d'association de cours
	 * @param id: l'id de l'étudiant
	 * @param model
	 * @return
	 */
	@GetMapping("/addStudentCourse/{id}")
	public String addStudentCourse(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute( "student", studentService.getStudent(id) );
		model.addAttribute("courseList", courseService.getAllCourses());
		return "addStudentCourse";
	}
	
	/**********************************************
	 ****** Méthodes de traitement des données ****
	 **********************************************/
	
	/**
	 * Méthode qui enregistre un étudiant en base de données
	 * @param student: L'objet student à enregistrer
	 * @param model
	 * @return RedirectView
	 */
	@PostMapping("/storeStudent")
	public RedirectView storeStudent(@Validated @ModelAttribute("student")Student student, Model model) {
		studentService.createStudent(student);
		model.addAttribute("studentList", studentService.getAllStudents());
		return new RedirectView("/home");
	}
	
	/**
	 * Méthode qui mets à jour un étudiant
	 * @param id: l'id de l'étudiant à modifier
	 * @param student: l'objet student à modifier
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@PostMapping("/editStudent/{id}")
	public String editStudent(@PathVariable(name = "id") Long id, @Validated @ModelAttribute("student")Student student, Model model) {
		studentService.updateStudent(id, student);
		model.addAttribute("studentList", studentService.getAllStudents());
		return "default";
	}
	
	/**
	 * Méthode qui supprime un étudiant
	 * @param id: l'id de l'étudiant à supprimer
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/deleteStudent/{id}")
	public RedirectView deleteStudent(@PathVariable(name = "id") Long id, Model model) {
		studentService.deleteStudent(id);
		model.addAttribute("studentList", studentService.getAllStudents());
		return new RedirectView("/home");
	}
	
	/**
	 * Méthode qui recherche un étudiant
	 * @param id: L'id de l'étudiant à rechercher
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@PostMapping("/searchStudent")
	public String searchStudent(@Validated @ModelAttribute("id")Long id, Model model) {
		if(studentService.getStudentSearch(id) instanceof Student) {
			model.addAttribute("student", studentService.getStudentSearch(id));
		}else {
			ErrorMessage errorMessage = (ErrorMessage) studentService.getStudentSearch(id);
			model.addAttribute("message", errorMessage.getMessage());
		}
		return "searchStudent";
	}
	
	/**
	 * Méthode qui associe un cours à un étudiant
	 * @param id: L'id de l'étudiant
	 * @param course: L'objet course
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@PostMapping("/setStudentCourse")
	public String setStudentCourse(@ModelAttribute("id") Long id, @ModelAttribute("cour") String course, Model model) {
		Course courseFound = courseService.getCourse(course);
		
		courseService.associateCourse(courseFound, id);
		
		model.addAttribute("studentList", studentService.getAllStudents());
		return "default";
	}
}
