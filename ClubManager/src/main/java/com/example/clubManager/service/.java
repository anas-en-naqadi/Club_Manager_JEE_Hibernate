package com.example.clubManager.service;

import org.hibernate.Session;
import com.example.clubManager.dao.MemberEventDao;
import com.example.clubManager.model.MemberEvent;

public class MemberEventService {

    private MemberEventDao memberEventDao;

    public MemberEventService(Session session) {
        this.memberEventDao = new MemberEventDao(session);
    }
}