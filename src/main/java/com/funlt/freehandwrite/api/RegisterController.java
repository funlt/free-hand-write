package com.funlt.freehandwrite.api;

import com.funlt.freehandwrite.common.enums.MemberEnum;
import com.funlt.freehandwrite.common.response.Result;
import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.dto.RegisterDTO;
import com.funlt.freehandwrite.service.MemberService;
import com.funlt.freehandwrite.util.Sm4Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * @author leotoa
 */
@RestController
public class RegisterController {

    @Autowired
    private MemberService memberService;

    @Value("${mail.check.key}")
    private String checkKey;

    /**
     * 注册接口
     * * @param registerDTO
     *
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDTO registerDTO) throws MessagingException {
        boolean flag = memberService.register(registerDTO);
        if (flag) {
            return new Result<>().ok("注册成功,请查看邮箱完成激活");
        } else {
            return new Result<>().fail("注册失败");
        }
    }

    /**
     * 用户激活
     * @param email
     * @param checkCode
     * @return
     */
    @GetMapping("/active")
    public Result<?> active(@RequestParam String email, @RequestParam String checkCode) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(checkCode)) {
            return new Result<>().fail("未知的激活链接");
        }
        Member member = memberService.getMemberByEmail(email);
        if (member != null && MemberEnum.INACTIVE.getCode().equals(member.getStatus())) {
            String username = member.getUsername();
            String decode = Sm4Util.sm4Decode(checkCode, checkKey);
            if (decode.equals(email + username)) {
                member.setStatus(MemberEnum.NORMAL.getCode());
                memberService.updateStatus(member);
                return new Result<>().ok("激活成功");
            }
        }
        return new Result<>().fail("激活失败");
    }
}
