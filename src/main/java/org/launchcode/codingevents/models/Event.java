package org.launchcode.codingevents.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    private final List<Tag> tags=new ArrayList<>();

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

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
}
