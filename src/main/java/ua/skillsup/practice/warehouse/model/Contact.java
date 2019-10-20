package ua.skillsup.practice.warehouse.model;

public class Contact {
    private short contactTypeId;
    private String contactValue;

    public Contact () {
    }

    public Contact(short contactTypeId, String contactValue) {
        this.contactTypeId = contactTypeId;
        this.contactValue = contactValue;
    }

    public short getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(short contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }
}
