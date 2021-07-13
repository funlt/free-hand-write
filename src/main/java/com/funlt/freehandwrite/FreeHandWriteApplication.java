package com.funlt.freehandwrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.funlt.freehandwrite.dao")
@SpringBootApplication
public class FreeHandWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeHandWriteApplication.class, args);
    }

}
