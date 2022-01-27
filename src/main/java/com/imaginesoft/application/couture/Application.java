package com.imaginesoft.application.couture;

import com.imaginesoft.application.couture.util.MapperWrapper;
import com.imaginesoft.application.couture.util.ModelMapperWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
