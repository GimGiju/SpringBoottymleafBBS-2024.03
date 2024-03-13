package com.example.abbs.service;

import com.example.abbs.entity.Board;

import java.util.List;

public interface BoardService {
    public static final int COUNT_PER_PAGE = 3;// 한 페이지당 글 목록의 갯수
    public static final int PAGE_PER_SCREEN = 10;	// 한 화면에 표시되는 페이지 갯수

    Board getBoard(int bid);

    int getBoardCount(String filed, String query);      // 전체 글의 수가 몇개인지 보여줌

    List<Board> getBoardList(int page, String field, String query);

    void insertBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(int bid);

    void increaseViewCount(int bid);

    void increaseReplyCount(int bid);

    void increaseLikeCount(int bid);

    void updateLikeCount(int bid, int count);


}
