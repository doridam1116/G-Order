package com.gorder.api.calendar.repository;

import com.gorder.api.calendar.model.Calendar;
import com.gorder.api.calendar.model.CalendarList;

import java.util.List;

public interface CalendarRepository {
    int insertCard(Calendar calendar);

    int insertList(CalendarList calendarList);

    List<CalendarList> selectCalendarList(String accountSerial);

    int deleteCalendarList(int calendarListNo);

    int deleteCalendarByNo(int calendarNo);
}
