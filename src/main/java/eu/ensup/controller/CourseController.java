package eu.ensup.controller;

import java.util.List;

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
import eu.ensup.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService courseService;
	
	/********************************
	 ****** Méthodes d'affichages****
	 ********************************/
	
	/**
	 * Méthode qui affiche la page de liste des cours
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/course")
	public String show(Model model) {
		List<Course> courseList = courseService.getAllCourses();
		System.out.println(courseList.get(0).toString());
		model.addAttribute("courseList", courseList);
		return "listCourse";
	}
	
	/**
	 * Méthode qui affiche le détail d'un cours
	 * @param theme: l'id du cours à afficher
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/showCourse/{theme}")
	public String showCourse(@PathVariable(name = "theme") String theme, Model model) {
		model.addAttribute( "course", courseService.getCourse(theme) );
		return "showCourse";
	}
	
	/**
	 * Méthode qui affiche la page de modification du cours
	 * @param theme: L'id du cours à modifier
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/updateCourse/{theme}")
	public String updateCourse(@PathVariable(name = "theme") String theme, Model model) {
		model.addAttribute( "course", courseService.getCourse(theme) );
		return "updateCourse";
	}
	
	/**
	 * Méthode qui affiche la page de création de cours
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/createCourse")
	public String createCourse() {
		return "createCourse";
	}
	
	
	
	/**********************************************
	 ****** Méthodes de traitement des données ****
	 **********************************************/
	
	/**
	 * Méthode qui enregistre un cours en base de données
	 * @param course: le cours à insérer
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@PostMapping("/storeCourse")
	public RedirectView storeCourse(@Validated @ModelAttribute("course")Course course, Model model) {
		courseService.createCourse(course);
		model.addAttribute("courseList", courseService.getAllCourses());
		return new RedirectView("/course");
	}
	
	/**
	 * Méthode qui supprime un cours dans la base de données
	 * @param theme: L'id du cours à supprimer
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@GetMapping("/deleteCourse/{theme}")
	public RedirectView deleteCourse(@PathVariable(name = "theme") String theme, Model model) {
		courseService.deleteCourse(theme);
		model.addAttribute("courseList", courseService.getAllCourses());
		return new RedirectView("/course");
	}
	
	/**
	 * Méthode qui mets à jour un cours dans la base de données
	 * @param theme: L'id du cours à modifier
	 * @param course: Le cours à supprimer
	 * @param model
	 * @return String: Le nom du fichier html
	 */
	@PostMapping("/editCourse/{theme}")
	public String editCourse(@PathVariable(name = "theme") String theme, @Validated @ModelAttribute("course")Course course, Model model) {
		courseService.updateCourse(theme, course);
		model.addAttribute("courseList", courseService.getAllCourses());
		return "listCourse";
	} 
}
