package com.gorder.api.calendar.controller;

import com.gorder.api.calendar.model.Calendar;
import com.gorder.api.calendar.model.CalendarList;
import com.gorder.api.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/card")
    public ResponseEntity<?> addCard(
            @RequestBody Calendar calendar
    ){
        return calendarService.addCard(calendar);
    }

    @PostMapping("/list")
    public ResponseEntity<?> addList(
            @RequestBody CalendarList calendarList
            ){
        return calendarService.addList(calendarList);
    }

    public ResponseEntity<?> add(){
        return ResponseEntity.ok().body("asd");
    }

    @GetMapping
    public ResponseEntity<?> getCalendar(
            @RequestParam String accountSerial
    ){
        return calendarService.getCalendar(accountSerial);
    }

    @DeleteMapping("/list/{calendarListNo}")
    public ResponseEntity<?> deleteCalendarList(
            @PathVariable int calendarListNo
    ){
        return calendarService.deleteCalendarList(calendarListNo);
    }

    @DeleteMapping("/{calendarNo}")
    public ResponseEntity<?> deleteCalendar(
            @PathVariable int calendarNo
    ){
        return calendarService.deleteCalendarByNo(calendarNo);
    }
}
