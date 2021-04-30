package eu.ensup.domaine;

/**
 * Classe métier représentant un utilisateur.
 * 
 * @author 33651
 *
 */
public class User
{
	private int id;
	private String login;
	private String password;
	private String profil;

	public User()
	{
		super();
	}

	public User(String login, String password)
	{
		this.login = login;
		this.password = password;
	}

	public User(int id, String login, String password, String profil)
	{
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getProfil()
	{
		return profil;
	}

	public void setProfil(String profil)
	{
		this.profil = profil;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", profil=" + profil + "]";
	}
}
