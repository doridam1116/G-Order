package com.gaubiz.gorder.api.calendar.controller;

import com.gaubiz.gorder.api.calendar.service.CalendarService;
import com.gaubiz.gorder.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/card")
    public ResponseEntity<?> addCard(){
        return null;
    }
}
