package com.example.abbs.entity;

import java.util.List;

public class SchDay {
    private int day;            // 1 ~ 31 까지 날짜
    private int date;           // 요일, 0 - 일요일, ..... 6 - 토요일
    private int isHoliday;      // 이 날이 휴일인지 물어봄        // day ~ isholiday 까지 날짜의 색을 표시하기 위해서
    private int isOtherMonth;    // 지난달 날짜나 다음달 날짜 조금 나오는거 표현하기 위해서 만든부분
    private String sdate;         // 20240313 켈린더에 사용하기 위해서 만든 부분
    private List<String> annivList;     // 
    private List<Schedule> schedList;      //

    public SchDay() { }

    public SchDay(int day, int date, int isHoliday, int isOtherMonth, String sdate, List<String> annivList, List<Schedule> schedList) {
        this.day = day;
        this.date = date;
        this.isHoliday = isHoliday;
        this.isOtherMonth = isOtherMonth;
        this.sdate = sdate;
        this.annivList = annivList;
        this.schedList = schedList;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(int isHoliday) {
        this.isHoliday = isHoliday;
    }

    public int getIsOtherMonth() {
        return isOtherMonth;
    }

    public void setIsOtherMonth(int isOtherMonth) {
        this.isOtherMonth = isOtherMonth;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public List<String> getAnnivList() {
        return annivList;
    }

    public void setAnnivList(List<String> annivList) {
        this.annivList = annivList;
    }

    public List<Schedule> getSchedList() {
        return schedList;
    }

    public void setSchedList(List<Schedule> schedList) {
        this.schedList = schedList;
    }
}
