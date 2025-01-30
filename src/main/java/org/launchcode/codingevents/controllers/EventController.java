package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping
    public String showEvents(@RequestParam(required = false) Integer categoryId, Model model) {
        if(categoryId==null) {
           // model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        }else {
            Optional<EventCategory> result=eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: "+categoryId);
            }else {
                EventCategory category=result.get();
                model.addAttribute("title","Events in category: "+category.getName());
                model.addAttribute("events",category.getEvents());
            }
        }
        return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute(new Event());
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/create";
    }

    //lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors,Model model) {
        if(errors.hasErrors()){
            model.addAttribute("events", eventRepository.findAll());
            return "events/create";
        }
        eventRepository.save(newEvent);
        return ("redirect:/events");
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        //model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
        }
        return "events/detail";
    }
}
