package com.funlt.freehandwrite.dao;

import com.funlt.freehandwrite.domain.Member;

import java.util.List;

public interface MemberDao {

    int addMember(Member member);

    List<Member> selectMemberList();

}
