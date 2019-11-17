package ua.skillsup.practice.warehouse.model;

import java.io.Serializable;

public class Category implements Serializable {
    private short categoryId;
    private String value;

    public Category() {
    }

    public Category(short categoryId, String value) {
        this.categoryId = categoryId;
        this.value = value;
    }

    public short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(short categoryId) {
        this.categoryId = categoryId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
