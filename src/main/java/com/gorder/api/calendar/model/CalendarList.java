package com.gorder.api.calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarList {
    private int calendarListNo;
    private String calendarListName;
    private String accountSerial;
    private List<Calendar> calendar;
}
