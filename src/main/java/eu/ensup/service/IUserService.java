package eu.ensup.service;

import eu.ensup.domaine.User;

public interface IUserService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.ensup.jpaGestionEnsup.service.IUserService#getUser(java.lang.String,
	 * java.lang.String)
	 */
	User getUser(String login, String password);

}