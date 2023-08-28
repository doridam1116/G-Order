package com.gorder.api.calendar.repository.logic;

import com.gorder.api.calendar.model.Calendar;
import com.gorder.api.calendar.model.CalendarList;
import com.gorder.api.calendar.repository.CalendarRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalendarRepositoryLogic implements CalendarRepository {
    private final SqlSession session;

    public CalendarRepositoryLogic(SqlSession session) {
        this.session = session;
    }

    @Override
    public int insertCard(Calendar calendar) {
        return session.insert("CalendarMapper.insertCard",calendar);
    }

    @Override
    public int insertList(CalendarList calendarList) {
        return session.insert("CalendarMapper.insertList",calendarList);
    }

    @Override
    public List<CalendarList> selectCalendarList(String accountSerial) {
        return session.selectList("CalendarMapper.selectCalendarList", accountSerial);
    }

    @Override
    public int deleteCalendarList(int calendarListNo) {
        return session.delete("CalendarMapper.deleteCalendarList",calendarListNo);
    }

    @Override
    public int deleteCalendarByNo(int calendarNo) {
        return session.delete("CalendarMapper.deleteCalendarByNo",calendarNo);
    }
}
