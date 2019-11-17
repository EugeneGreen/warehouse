package ua.skillsup.practice.warehouse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Product {

	private Long id;
	private String title;
	private String description;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime dateCreate;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private LocalDateTime dateUpdate;
	private ArrayList<Category> categories = new ArrayList<>();
	private int count;

	public Product() {
	}

	public Product(Long id, String title, String description, ArrayList<Category> categories, int count) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.categories = categories;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
                ", categories=" + categories +
				", dateCreate=" + dateCreate +
				", dateUpdate=" + dateUpdate +
				'}';
	}
}