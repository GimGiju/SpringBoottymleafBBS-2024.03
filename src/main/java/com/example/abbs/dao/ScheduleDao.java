package com.example.abbs.dao;

import com.example.abbs.entity.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleDao {

    @Select("select * from schedule where sid=#{sid}")
    Schedule getSchedule(int sid);

    @Select("SELECT * FROM schedule" +
            "  WHERE uid=#{uid} AND sdate BETWEEN #{startDay} AND #{endDay}" +
            "  ORDER BY sdate, startTime")
    List<Schedule> getSchedList(String uid, String startDay, String endDay);

    @Insert("insert into schedule values"
            + " ( default, #{uid}, #{sdate}, #{title}, #{place}, #{startTime}, #{endTime}"
            + " #{isImportant}, #{memo})")
    void insertSchedule(Schedule sched);

    @Update("update schedule set  sdate=#{sdate}, title=#{title}, place=#{place}"
            + " #{startTime=#{startTime}, endTime=#{endTime}, isImportant=#{isImportant},"
            + " memo=#{memo} where sid=#{sid}")
    void updateSchedule(Schedule sched);

    @Delete("delete from schedule where sid=#{sid}")
    void deleteSchedule(int sid);

}
