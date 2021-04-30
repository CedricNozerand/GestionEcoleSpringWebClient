package eu.ensup.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import eu.ensup.domaine.Course;
import eu.ensup.domaine.Student;

@Service
public class CourseService implements ICourseService {
	
	// Fields

	private static final Logger LOG = LogManager.getLogger(CourseService.class);

	private static final String URL = "http://localhost:8081/SpringMVC/servlet/";

	// Constructors

	/**
	 * Construit le CourseService en fonction de l'entityManager.
	 * 
	 * @param entityManager
	 */
	public CourseService()
	{
		super();
		LOG.info("Démarrage du service CourseService");
	}

	/**
	 * Insert un cours en base de données
	 * @param objet course
	 */
	@Override
	public void createCourse(Course course)
	{
		LOG.info("Appel de la méthode createCourse() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("course/create");

//		String input = student.toJson();
		
		Response response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String input;
		
		try {
			input = objectMapper.writeValueAsString(course);
			response = webTarget.request("application/json").post(Entity.json(input));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Méthode qui associe un cours à un étudiant
	 * @param objet course
	 * @param id du cours
	 */
	@Override
	public void associateCourse(Course course, Long id)
	{
		LOG.info("Appel de la méthode associateCourse() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("student/addCourse/" + id);			
		
		Response response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String input;
		
		try {
			input = objectMapper.writeValueAsString(course);
			response = webTarget.request("application/json").put(Entity.json(input));
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Récupère tous les cours en base de données
	 * @return List<Course>
	 */
	@Override
	public List<Course> getAllCourses()
	{
		LOG.info("Appel de la méthode getAllCourses() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("course/getAll");

		Response response = webTarget.request("application/json").get();

		return response.readEntity(new GenericType<List<Course>>()
		{
		});
	}
	
	/**
	 * Supprime un cours en base de données
	 * @param theme: theme du cours
	 */
	@Override
	public void deleteCourse(String theme)
	{
		LOG.info("Appel de la méthode deleteCourse() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("course/delete/" + theme);
		
		Response response = webTarget.request("application/json").delete();
	}
	
	/**
	 * Récupère un cours en fonction du thème
	 * @param theme: theme du cours
	 * @return objet course
	 */
	@Override
	public Course getCourse(String theme)
	{
		LOG.info("Appel de la méthode getCourse() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("course/detail/" + theme);

		Response response = webTarget.request("application/json").get();
		
		return response.readEntity(Course.class);
	}
	
	/**
	 * Mets à jour un cours
	 * @param theme: theme du cours
	 * @param course: objet course
	 */
	@Override
	public void updateCourse(String theme, Course course)
	{
		LOG.info("Appel de la méthode updateCourse() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("course/update/" + theme);

		Response response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String input;
		
		try {
			input = objectMapper.writeValueAsString(course);
			response = webTarget.request("application/json").put(Entity.json(input));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Response response = webTarget.request("application/json").put(Entity.json(input));
	}
}
