package ua.skillsup.practice.warehouse.model;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Long id;
	@Size(min = 3, max = 50, message = "Size should be from 3 to 50 symbols")
	private String name;
	private String description;
	private ArrayList<Contact> contacts = new ArrayList<>();
	private List<Product> products;

	public Client() {

	}

	public Client(@Size(min = 3, max = 50, message = "Size should be from 3 to 50 symbols") String name, String description, ArrayList<Contact> contacts, List<Product> products) {
		this.name = name;
		this.description = description;
		this.contacts = contacts;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", products=" + products +
				'}';
	}
}