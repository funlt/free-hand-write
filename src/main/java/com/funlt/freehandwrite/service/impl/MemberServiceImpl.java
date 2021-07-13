package com.funlt.freehandwrite.service.impl;

import com.funlt.freehandwrite.dao.MemberDao;
import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean register(Member member) {

        return false;
    }
}
