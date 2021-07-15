package com.funlt.freehandwrite.service;

import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.dto.RegisterDTO;

import javax.mail.MessagingException;

public interface MemberService {

    public boolean register(RegisterDTO member) throws MessagingException;

    Member getMemberByEmail(String email);

    void updateStatus(Member member);
}
