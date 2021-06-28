package com.funlt.freehandwrite.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @RequestMapping("/handwrite")
    public String test(){
        return "游离笔迹";
    }


    @RequestMapping("/git")
    public String git(){
        return "游离笔迹git";
    }
}
