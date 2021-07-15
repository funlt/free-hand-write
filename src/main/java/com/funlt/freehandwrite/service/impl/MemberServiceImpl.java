package com.funlt.freehandwrite.service.impl;

import com.funlt.freehandwrite.common.enums.MemberEnum;
import com.funlt.freehandwrite.dao.MemberDao;
import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.dto.RegisterDTO;
import com.funlt.freehandwrite.service.MemberService;
import com.funlt.freehandwrite.util.CustomStrUtil;
import com.funlt.freehandwrite.util.Sm4Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail.check.key}")
    private String checkKey;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean register(RegisterDTO member) throws MessagingException {
        Member newMember = new Member();
        String email = member.getEmail();
        String password = member.getPassword();
        String username = member.getUsername();
        String mixPassword = mixPwd(password);
        newMember.setId(CustomStrUtil.getRandomString(8));
        newMember.setUsername(username);
        newMember.setPassword(mixPassword);
        newMember.setEmail(email);

        newMember.setStatus(MemberEnum.INACTIVE.getCode());
        int result = memberDao.addMember(newMember);
        if(result > 0){
            //发送激活邮件
            sendMemberEmail(email,username);
        }else{
            return false;
        }
        return true;
   }

    private void sendMemberEmail(String email,String username) throws MessagingException {
        String needEncryptStr = email+username;
        String checkCode = Sm4Util.sm4Encode(needEncryptStr,checkKey);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessage.setFrom(from);

        messageHelper.setTo(email);

        messageHelper.setSubject("激活邮件");

        messageHelper.setText("<html>\n" +
                "<body>\n" +
                "    <h1>您已经完成注册，请点击链接激活<a>localhost:8080/active?email="+email+"&checkCode="+checkCode+"</a></h1>\n" +
                "</body>\n" +
                "</html>",true);
        try {
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        }catch (MailException e){
            log.info("邮件发送失败,{}",e.getMessage());
        }
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.selectMemberByEmail(email);
    }

    @Override
    public void updateStatus(Member member) {
        memberDao.updateStatus(member);
    }

    private String mixPwd(String password) {
        String key = CustomStrUtil.getRandomString(16);
        return Sm4Util.sm4Encode(password, key);
    }
}
