package com.tsystems.study.jamboree.service;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.study.jamboree.model.Event;
import com.tsystems.study.jamboree.model.User;
import com.tsystems.study.jamboree.repository.EventRepository;
import com.tsystems.study.jamboree.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private static final int MINIMUM_DAYS_BEFORE = 1;
    private static final int MAXIMUM_DAYS_BEFORE = 10;

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

    public boolean isPossibleToApply(Date startDate) {
        Date now =  new Date();
        return ChronoUnit.DAYS.between(now.toInstant(), startDate.toInstant()) > MINIMUM_DAYS_BEFORE
            && ChronoUnit.DAYS.between(now.toInstant(), startDate.toInstant()) < MAXIMUM_DAYS_BEFORE;
    }

    public void applyToEvent(Event event, User user) throws JamboreeException {
        // check event date
        if (!isPossibleToApply(event.getStartDate())) {
            throw new JamboreeException("Only possible to apply not before than at minimum "
                    + MINIMUM_DAYS_BEFORE + " days and at maximum " + MAXIMUM_DAYS_BEFORE + " days");
        }

        if (event.getParticipants() == null) {
            event.setParticipants(new HashSet<>());
        }

        if (event.getParticipants().contains(user)) {
            throw new JamboreeException("Already participant");
        }

        event.getParticipants().add(user);

        eventRepository.save(event);
    }

    public void create(Event event) throws JamboreeException {
        if (event.getEndDate().before(event.getStartDate())) {
            throw new JamboreeException("End-Date must be past or equal to Start-Date");
        }
        eventRepository.save(event);
    }
}
