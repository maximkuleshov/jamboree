package com.tsystems.study.jamboree.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsystems.study.jamboree.model.ApplyRequest;
import com.tsystems.study.jamboree.model.Event;
import com.tsystems.study.jamboree.model.User;
import com.tsystems.study.jamboree.repository.UserRepository;
import com.tsystems.study.jamboree.service.EventService;
import com.tsystems.study.jamboree.service.JamboreeException;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public Event[] getEvents() {
        return eventService.findAll().toArray(new Event[0]);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> applyForEvent(@RequestBody ApplyRequest applyRequest) throws JamboreeException {
        System.out.println(applyRequest);

        Optional<User> userOptional = userRepository.findByLogin(applyRequest.getUserLogin());
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("No such user");
        }

        Optional<Event> eventOptional = eventService.findById(applyRequest.getEventId());
        if (!eventOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        eventService.applyToEvent(eventOptional.get(), userOptional.get());

        return ResponseEntity.ok("{}");
    }
}
