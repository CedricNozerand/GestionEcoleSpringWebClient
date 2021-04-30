package eu.ensup.domaine;

import java.util.Collection;

/**
 * Classe métier représentant un étudiant.
 * 
 * @author 33651
 *
 */
public class Student
{
	private int id;
	private String firstName;
	private String lastName;
	private String mailAddress;
	private String address;
	private String numberPhone;
	private String birthDate;

	Collection<Course> courses;

	public Student()
	{
		super();
	}

	/**
	 * Construit un Student.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param mailAddress
	 * @param address
	 * @param numberPhone
	 * @param birthDate
	 * @param courses
	 */
	public Student(String firstName, String lastName, String mailAddress, String address, String numberPhone,
			String birthDate, Collection<Course> courses)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mailAddress;
		this.address = address;
		this.numberPhone = numberPhone;
		this.birthDate = birthDate;
		this.courses = courses;
	}

	/**
	 * Construit un Student. (sans les cours)
	 * 
	 * @param firstName
	 * @param lastName
	 * @param mailAddress
	 * @param address
	 * @param numberPhone
	 * @param birthDate
	 * @param courses
	 */
	public Student(String firstName, String lastName, String mailAddress, String address, String numberPhone,
			String birthDate)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailAddress = mailAddress;
		this.address = address;
		this.numberPhone = numberPhone;
		this.birthDate = birthDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMailAddress()
	{
		return mailAddress;
	}

	public void setMailAddress(String mailAddress)
	{
		this.mailAddress = mailAddress;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getNumberPhone()
	{
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone)
	{
		this.numberPhone = numberPhone;
	}

	public Collection<Course> getCourses()
	{
		return courses;
	}

	public void setCourses(Collection<Course> courses)
	{
		this.courses = courses;
	}

	public String getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	@Override
	public String toString()
	{
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mailAddress="
				+ mailAddress + ", address=" + address + ", numberPhone=" + numberPhone + "]";
	}

	public String toJson()
	{
		return "{\"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"mailAddress\":\""
				+ mailAddress + "\", \"address\":\"" + address + "\"" + ", \"numberPhone\":" + "\"" + numberPhone
				+ "\", \"birthDate\":\"" + birthDate + "\"}";
	}
}
