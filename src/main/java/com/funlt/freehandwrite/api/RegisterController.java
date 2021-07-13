package com.funlt.freehandwrite.api;

import com.funlt.freehandwrite.common.response.Result;
import com.funlt.freehandwrite.domain.Member;
import com.funlt.freehandwrite.dto.RegisterDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leotoa
 */
@RestController
@RequestMapping("/api/v1/blog/register")
public class RegisterController {


    /**
     * 注册接口
     * @param registerDTO
     * @return
     */
    @PostMapping
    public Result<?> register(@RequestBody RegisterDTO registerDTO) {
        String password = registerDTO.getPassword();
        String username = registerDTO.getUsername();

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);


        return new Result<>().ok();
    }


}
