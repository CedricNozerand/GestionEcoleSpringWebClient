package eu.ensup.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import eu.ensup.domaine.Student;
import eu.ensup.service.StudentService;

@Controller
public class HomeController {
	
	@Autowired
	StudentService studentService;
	
	/**
	 * Méthode qui affiche la page d'accueil
	 * @param model
	 * @param session: L'objet de session
	 * @return RedirectView
	 */
	@GetMapping("/")
	public RedirectView index(Model model, HttpSession session) {

		if(session.getAttribute("user")!=null) {
			
			List<Student> studentList = studentService.getAllStudents();
			model.addAttribute("studentList", studentList);
			
			return new RedirectView("/home");
		} else {
			return new RedirectView("/login");
		}
	}
	
	/**
	 * Méthode qui affiche la page d'accueil
	 * @param model
	 * @returnString: Le nom du fichier html
	 */
	@GetMapping("/home")
	public String home(Model model) {
		List<Student> studentList = studentService.getAllStudents();
		model.addAttribute("studentList", studentList);
		return "default";
	}
}
