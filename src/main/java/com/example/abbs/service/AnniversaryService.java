package com.example.abbs.service;

import com.example.abbs.entity.Anniversary;

import java.util.List;

public interface AnniversaryService {

    List<Anniversary> getAnnivListByDay(String uid, String sdate);

    List<Anniversary> getAnnivList(String uid, String startDay, String endDay);

    void insertAnniv(Anniversary anniv);



}
