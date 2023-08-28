package com.gorder.api.calendar.service;

import com.gorder.api.calendar.model.Calendar;
import com.gorder.api.calendar.model.CalendarList;
import org.springframework.http.ResponseEntity;

public interface CalendarService {
    ResponseEntity<?> addCard(Calendar calendar);

    ResponseEntity<?> addList(CalendarList calendarList);

    ResponseEntity<?> getCalendar(String accountSerial);

    ResponseEntity<?> deleteCalendarList(int calendarListNo);

    ResponseEntity<?> deleteCalendarByNo(int calendarNo);
}
