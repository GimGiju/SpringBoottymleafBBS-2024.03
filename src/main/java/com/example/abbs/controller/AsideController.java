package com.example.abbs.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aside")
public class AsideController {

    @ResponseBody           // html 데이터 바로 보내주는 코드
    @GetMapping("/stateMsg")
    public String changeStateMsg(String stateMsg, HttpSession session){
        session.setAttribute("stateMsg", stateMsg);
        return "return message";
    }
    
}
