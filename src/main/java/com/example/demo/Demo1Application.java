package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@ComponentScan("com.example.demo.*")
/*
 * 启动类
 */
public class Demo1Application {
	public static void main(String[] args) {
		System.out.println("----------启动成功-----------") ;
		SpringApplication.run(Demo1Application.class, args);
	}

}
