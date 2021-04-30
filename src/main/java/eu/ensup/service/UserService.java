package eu.ensup.service;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import eu.ensup.domaine.User;

/**
 * Classe UserService : Fait le lien entre le lanceur et le DAO concernant les
 * utilisateurs.
 * 
 * @author 33651
 *
 */
@Service
public class UserService implements IUserService
{
	// Fields

	private static final Logger LOG = LogManager.getLogger(UserService.class);
    
    private static final String URL = "http://localhost:8081/SpringMVC/servlet/";

	// Constructors

	/**
	 * Construit le UserService en fonction d'un entityManager.
	 * 
	 * @param entityManager
	 */
	public UserService()
	{
		super();
		LOG.info("Démarrage du service UserService");
	}

	/**
	 * Récupère un utilisateur
	 * @param login
	 * @param password
	 * @return objet user
	 */
	@Override
	public User getUser(String login, String password)
	{
		LOG.info("Appel de la méthode getUser() du dao");
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonJsonProvider.class);
		
		Client client = ClientBuilder.newClient(clientConfig);
		
		User user = new User(login, password);
		
		
		WebTarget webTarget = client.target(URL).path("user/getUser");

		String input = "{\"login\":\"" + login + "\", \"password\":\"" + password + "\"}";

//		Response response = webTarget.request("application/json").post(Entity.json(input));
		
		Response response = webTarget.request("application/json").post(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		String output = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		User userResponse=null;
		
		try {
            JsonNode jsonNode = mapper.readTree(output);

            userResponse = mapper.readValue( jsonNode.get("user").toString(),User.class);

            return userResponse;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userResponse;
		
	}
}
