package com.example.abbs.dao;

import com.example.abbs.entity.Anniversary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnniversaryDao {

    @Select("select * from anniversary where( uid=#{uid} or uid='admin') and"
            + " adate between #{startDay} and #{endDay} order by adate")
    List<Anniversary> getAnnivList(String uid, String startDay, String endDay);

    @Insert("insert into anniversary value(default, #{uid}, #{aname}, #{adate}, #{isHoliday})")
    void insertAnniv(Anniversary anniv);

}
