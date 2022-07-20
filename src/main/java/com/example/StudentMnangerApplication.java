package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan({"com.example.dao","com.example.service"})

public class StudentMnangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentMnangerApplication.class, args);
    }
public  static void main(){
}
}
