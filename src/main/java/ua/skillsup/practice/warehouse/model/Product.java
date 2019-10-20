package ua.skillsup.practice.warehouse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
	private int count;

	public Product() {
	}

	public Product(Long id, String title, String description, int count) {
		this.id = id;
		this.title = title;
		this.description = description;
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
				", dateCreate=" + dateCreate +
				", dateUpdate=" + dateUpdate +
				'}';
	}
}