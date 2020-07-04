package lhz.schoolhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lhz.schoolhelper.mapper")
public class SchoolhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolhelperApplication.class, args);
	}

}
