package com.example.abbs.dao;

import com.example.abbs.entity.Like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LikeDao {

    @Select("select * from likes where bid=#{bid} and uid=#{uid}")
    Like getLike(int bid, String uid);

    @Select("select * from likes where lid=#{lid}")
    Like getLikeByLid(int lid);

    @Select("select * from likes where bid=#{bid}")
    List<Like> getLikeList(int bid);

    @Insert("insert into likes values(default, #{uid}, #{bid}, #{value})")
    void insertLike(Like like);

    // update likes set value=if(value=0,1,0) where lid=#{lid}
    @Update("update likes set value=#{value} where lid=#{lid}")
    void updateLike(Like like);

}
