package com.tsystems.study.jamboree.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.study.jamboree.model.Event;
import com.tsystems.study.jamboree.model.User;
import com.tsystems.study.jamboree.repository.EventRepository;
import com.tsystems.study.jamboree.repository.UserRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Collection<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(String id) {
        return eventRepository.findById(id);
    }

    public void applyToEvent(Event event, User user) throws JamboreeException {
        if (event.getParticipants() == null) {
            event.setParticipants(new HashSet<>());
        }

        if (event.getParticipants().contains(user)) {
            throw new JamboreeException("Already participant");
        }

        event.getParticipants().add(user);

        eventRepository.save(event);
    }
}
