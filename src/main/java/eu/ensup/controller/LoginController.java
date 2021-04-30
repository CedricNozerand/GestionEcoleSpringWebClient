package eu.ensup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import eu.ensup.domaine.User;
import eu.ensup.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Méthode qui affiche la page de connexion
	 * @return La le nom du fichier html
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * Méthode qui vérifie les informations de connexion entrées et qui redirige l'utilisateur en fonction de ces informations
	 * @param user: L'utilisateur qui se connecte
	 * @param model
	 * @param attributes: Objet qui permet de rediriger l'utilisateur
	 * @param session: Objet de session
	 * @return RedirectView
	 */
	@PostMapping("/getUser")
	public RedirectView getUser(@Validated @ModelAttribute("user")User user, Model model, RedirectAttributes attributes, HttpSession session) {
		User foundUser = userService.getUser(user.getLogin(), user.getPassword());
		if (foundUser!=null) {
			session.setAttribute("user", foundUser);
			return new RedirectView("/home");
		} else {
			attributes.addFlashAttribute("message", "Identifiants incorrect");
			attributes.addFlashAttribute("typeMessage", "error");
			return new RedirectView("/login");
		}
	}
}
