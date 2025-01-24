package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity{

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 25, message = "name must be between 3 and 25 characters.")
    private String name;

    @Size(max = 500, message = "Description too long")
    private String description;

    @NotBlank
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    private EventType type;

    public Event() {}

    public Event(String name, String description, String contactEmail, EventType type) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;
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

    public @Email String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(@Email String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
