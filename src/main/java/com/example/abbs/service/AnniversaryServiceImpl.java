package com.example.abbs.service;

import com.example.abbs.dao.AnniversaryDao;
import com.example.abbs.entity.Anniversary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnniversaryServiceImpl implements AnniversaryService {
    @Autowired private AnniversaryDao annivDao;

    @Override
    public List<Anniversary> getAnnivListByDay(String uid, String sdate) {
        return annivDao.getAnnivList(uid, sdate, sdate);
    }

    @Override
    public List<Anniversary> getAnnivList(String uid, String startDay, String endDay) {
        return annivDao.getAnnivList(uid, startDay, endDay);
    }

    @Override
    public void insertAnniv(Anniversary anniv) {
        annivDao.insertAnniv(anniv);
    }
}
