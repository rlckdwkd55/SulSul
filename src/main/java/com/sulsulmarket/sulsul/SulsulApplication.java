package com.sulsulmarket.sulsul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 서버실행
@SpringBootApplication
@MapperScan
public class SulsulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SulsulApplication.class, args);
	}

}
