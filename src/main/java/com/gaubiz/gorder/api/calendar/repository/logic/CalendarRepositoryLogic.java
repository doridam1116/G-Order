package com.gaubiz.gorder.api.calendar.repository.logic;

import com.gaubiz.gorder.api.calendar.repository.CalendarRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarRepositoryLogic implements CalendarRepository {
    private SqlSession session;

    public CalendarRepositoryLogic(SqlSession session) {
        this.session = session;
    }
}
