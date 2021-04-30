package eu.ensup.service;

import java.io.IOException;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import eu.ensup.domaine.ErrorMessage;
import eu.ensup.domaine.Student;

@Service
public class StudentService implements IStudentService {
	
	// Fields

	private static final Logger LOG = LogManager.getLogger(StudentService.class);

	private static final String URL = "http://localhost:8081/SpringMVC/servlet/";

	// Constructors

	public StudentService()
	{
		super();
		LOG.info("Démarrage du service StudentService");
	}

	// Methods

	/**
	 * Insert un étudiant en base de données
	 * @param object student
	 */
	@Override
	public void createStudent(Student student)
	{
		LOG.info("Appel de la méthode createStudent() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("student/create");

//			String input = student.toJson();
		
		Response response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String input;
		
		try {
			input = objectMapper.writeValueAsString(student);
			response = webTarget.request("application/json").post(Entity.json(input));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Récupère un étudiant en fonction de son ID
	 * @param id: l'id de l'étudiant
	 * @return Objet student
	 */
	@Override
	public Student getStudent(Long id)
	{
		LOG.info("Appel de la méthode getStudent() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("student/detail/" + id);

		Response response = webTarget.request("application/json").get();
		
		return response.readEntity(Student.class);
	}
	
	/**
	 * Récupère un étudiant en fonction de la recherche
	 * @param id: l'id de l'étudiant
	 * @return Object
	 */
	@Override
	public Object getStudentSearch(Long id) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URL).path("student/search/"+id);

        Response response = webTarget.request("application/json").get();

        String output = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Student studentResponse=null;
        ErrorMessage errorMessage = null;
        try {
            JsonNode jsonNode = mapper.readTree(output);
            if(jsonNode.size() > 4) {
                studentResponse = mapper.readValue(jsonNode.toString(), Student.class);
                        return studentResponse;
            }
            else {
                errorMessage = mapper.readValue(jsonNode.toString(), ErrorMessage.class);
                return errorMessage;
            }


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	/**
	 * Récupère un étudiant en fonction de son adresse mail
	 * @param mail: le mail de l'étudiant
	 * @return objet student
	 */
	@Override
	public Student getStudentByMail(String mail)
	{
		LOG.info("Appel de la méthode getStudentByMail() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("student/getByMail/" + mail);

		Response response = webTarget.request("application/json").get();
		
		return response.readEntity(Student.class);
	}

	/**
	 * Récupère tous les étudiants en base de données
	 * @return List<Student>
	 */
	@Override
	public List<Student> getAllStudents()
	{
		LOG.info("Appel de la méthode getAllStudents() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("student/getAll");

		Response response = webTarget.request("application/json").get();

		return response.readEntity(new GenericType<List<Student>>()
		{
		});
	}

	/**
	 * Supprime un étudiant en fonction de son ID
	 * @param ID l'id de l'étudiant
	 */
	@Override
	public void deleteStudent(Long id)
	{
		LOG.info("Appel de la méthode deleteStudent() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("student/delete/" + id);

		Response response = webTarget.request("application/json").delete();
	}

	/**
	 * Mets à jour un étudiant en fonction de son ID
	 * @param oldStudentID: id de l'étudiant
	 * @param student: le nouvelle étudiant
	 */
	@Override
	public void updateStudent(Long oldStudentId, Student student)
	{
		LOG.info("Appel de la méthode updateStudent() du dao");

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(URL).path("student/update/" + oldStudentId);

		Response response = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		String input;
		
		try {
			input = objectMapper.writeValueAsString(student);
			response = webTarget.request("application/json").put(Entity.json(input));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//			Response response = webTarget.request("application/json").put(Entity.json(input));
	}

	/**
	 * Recherche un ou plusieurs étudiants en fonction de leur données.
	 * 
	 * @param firstName Le prénom du ou des étudiants à chercher.
	 * @param lastName  Le nom de famille du ou des étudiants à chercher.
	 * @return La liste des étudiants correspondant au prénom et au nom entrés.
	 */
	@Override
	public List<Student> searchStudent(String firstName, String lastName)
	{
		LOG.info("Appel de la méthode searchStudent() du dao");
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(URL).path("student/search/")
				.queryParam("firstName", firstName).queryParam("lastName", lastName);

		Response response = webTarget.request("application/json").get();

		return response.readEntity(new GenericType<List<Student>>()
		{
		});
	}
}
