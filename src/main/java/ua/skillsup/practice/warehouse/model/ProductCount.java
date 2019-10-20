package ua.skillsup.practice.warehouse.model;

public class ProductCount {

	private int value;

	public ProductCount() {

	}

	public ProductCount(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ProductCount{" +
				"value=" + value +
				'}';
	}
}