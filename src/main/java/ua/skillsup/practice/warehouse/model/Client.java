package ua.skillsup.practice.warehouse.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Client {
	private Long id;
	@Size(min = 3, max = 50, message = "Size should be from 3 to 50 symbols")
	private String firstName;
	private String lastName;
	private String company;
	private ArrayList<Contact> contacts = new ArrayList<>();
	private List<Product> products;

	public Client() {

	}

	public Client(@Size(min = 3, max = 50, message = "Size should be from 3 to 50 symbols") String firstName, String company, ArrayList<Contact> contacts, List<Product> products) {
		this.firstName = firstName;
		this.company = company;
		this.contacts = contacts;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isCorrect() {
		if(firstName == null || lastName == null || contacts == null || contacts.size() == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", company='" + company + '\'' +
                ", contacts=" + contacts +
				", products=" + products +
				'}';
	}
}