package com.example.abbs.service;

import com.example.abbs.entity.SchDay;
import com.example.abbs.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule getSchedule(int sid);

    List<Schedule> getScheduleList(String uid, String startDay, String endDay);

    List<Schedule> getScheduleList(String uid, String sdate);

    void insertSchedule(Schedule schedule);

    void updateSchedule(Schedule schedule);

    void deleteSchedule(int sid);

    SchDay generateSchDay(String uid, int day, String sdate, int date, int isOtherMonth);

}
