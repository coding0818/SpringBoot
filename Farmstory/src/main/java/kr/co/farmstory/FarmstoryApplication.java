package kr.co.farmstory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.co.farmstory.dao")
public class FarmstoryApplication {

	public static void main(String[] args) {

		SpringApplication.run(FarmstoryApplication.class, args);
	}

}
