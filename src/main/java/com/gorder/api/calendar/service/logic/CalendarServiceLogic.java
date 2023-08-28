package com.gorder.api.calendar.service.logic;

import com.gorder.api.calendar.model.Calendar;
import com.gorder.api.calendar.model.CalendarList;
import com.gorder.api.calendar.repository.CalendarRepository;
import com.gorder.api.calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.gorder.config.PropertyConfig.getMessageSource;

@Service
public class CalendarServiceLogic implements CalendarService {
    private final CalendarRepository calendarRepository;

    public CalendarServiceLogic(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public ResponseEntity<?> addCard(Calendar calendar) {
        int result = calendarRepository.insertCard(calendar);
        if (result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null, Locale.getDefault()));
    }

    @Override
    public ResponseEntity<?> addList(CalendarList calendarList) {
        int result = calendarRepository.insertList(calendarList);
        if(result > 0){
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null, Locale.getDefault()));
    }

    @Override
    public ResponseEntity<?> getCalendar(String accountSerial) {
        List<CalendarList> calendarLists = calendarRepository.selectCalendarList(accountSerial);
        return ResponseEntity.ok().body(calendarLists);
    }

    @Override
    public ResponseEntity<?> deleteCalendarList(int calendarListNo) {
        int result = calendarRepository.deleteCalendarList(calendarListNo);
        if(result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK",null,Locale.getDefault()));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR",null,Locale.getDefault()));
    }

    @Override
    public ResponseEntity<?> deleteCalendarByNo(int calendarNo) {
        int result = calendarRepository.deleteCalendarByNo(calendarNo);
        if(result > 0) {
            return ResponseEntity.ok().body(getMessageSource().getMessage("HTTP_OK", null, Locale.getDefault()));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getMessageSource().getMessage("HTTP_SERVER_ERROR", null, Locale.getDefault()));
    }

}
