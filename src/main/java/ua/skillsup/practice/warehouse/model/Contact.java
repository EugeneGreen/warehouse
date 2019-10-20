package ua.skillsup.practice.warehouse.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private short contactTypeId;
    private String value;

    public Contact () {
    }

    public Contact(short contactTypeId, String value) {
        this.contactTypeId = contactTypeId;
        this.value = value;
    }

    public short getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(short contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
