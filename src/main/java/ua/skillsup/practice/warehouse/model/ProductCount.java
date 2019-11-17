package ua.skillsup.practice.warehouse.model;

public class ProductCount {
	private int id;
	private int count;

	public ProductCount() {

	}

	public ProductCount(int count) {
		this.count = count;
	}

	public ProductCount(int id, int count) {
		this.id = id;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ProductCount{" +
				"id=" + id +
				"count=" + count +
				'}';
	}
}