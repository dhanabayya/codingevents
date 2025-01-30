package org.launchcode.codingevents.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity{

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 25, message = "name must be between 3 and 25 characters.")
    private String name;

    @ManyToOne
    @NotNull(message = "Category is required.")
    private EventCategory eventCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    public Event() {}

    public Event(String name, EventCategory eventCategory,EventDetails eventDetails) {
        this.name = name;
        this.eventCategory = eventCategory;
        this.eventDetails=eventDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public @Valid EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(@Valid EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}
