package com.example.abbs.service;

import com.example.abbs.entity.Like;

import java.util.List;

public interface LikeService {

    Like getLike(int bid, String uid);

    Like getLikeByLid(int lid);

    List<Like> getLikeList(int bid);

    void insertLike(Like like);

    void toggleLike(Like like);         // toggle -> value가 0이면 1로 바꾸고, 1이면 0으로 바꾸는걸 toggle이라함

    int getLikeCount(int bid);          // bid에 대해서 좋아요가 몇개가 눌렸는지 확인
    
}
