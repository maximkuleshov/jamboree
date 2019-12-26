package com.tsystems.study.jamboree.rest;

import com.tsystems.study.jamboree.dto.ApplyRequestDto;
import com.tsystems.study.jamboree.dto.EventDto;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @GetMapping("")
    public EventDto[] getEvents() {
        return new EventDto[]{
                new EventDto(1, "First", "First Description", new Date(), new Date()),
                new EventDto(2, "Second", "Second Description", new Date(), new Date())

        };
    }

    @PostMapping("")
    public void applyForEvent(@RequestBody ApplyRequestDto applyRequest) {
        System.out.println("Apply request: " + applyRequest);
    }
}
