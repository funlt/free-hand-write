package com.funlt.freehandwrite.api;

import com.funlt.freehandwrite.common.response.Result;
import com.funlt.freehandwrite.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {


    @GetMapping("test")
    public Result test(){
        return new Result().ok("test");
    }

    @GetMapping("/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return new Result<>().fail("用户不存在");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return new Result<>().fail("账号或密码错误");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return new Result<>().fail("没有权限");
        }
        return new Result<>().ok("登录成功");
    }

}
