package com.gaubiz.gorder.api.calendar.service.logic;

import com.gaubiz.gorder.api.calendar.repository.CalendarRepository;
import com.gaubiz.gorder.api.calendar.service.CalendarService;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceLogic implements CalendarService {
    private CalendarRepository calendarRepository;

    public CalendarServiceLogic(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }
}
