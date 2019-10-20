package ua.skillsup.practice.warehouse.repositories.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    private String title;

    public ContactTypeEntity() {
    }

    public ContactTypeEntity(short id, String title) {
        this.id = id;
        this.title = title;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
