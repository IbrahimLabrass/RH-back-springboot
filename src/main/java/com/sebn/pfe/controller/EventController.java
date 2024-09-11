package com.sebn.pfe.controller;

import com.sebn.pfe.exception.ResourceNotFoundException;
import com.sebn.pfe.model.Event; // Assuming you have an Event model class
import com.sebn.pfe.model.User;
import com.sebn.pfe.repository.EventRepository; // Assuming you have an EventRepository

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    private Optional<User> user;


    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + id));
        return ResponseEntity.ok().body(event);
    }

    @PostMapping("/event")
    public Event createEvent(@Valid @RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody Event eventDetails) throws ResourceNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + id));

        event.setTitle(eventDetails.getTitle());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setDescription(eventDetails.getDescription());
        // Update other fields as necessary
        final Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/events/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + id));

        eventRepository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
