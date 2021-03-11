package com.ecommerce.rooms;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RoomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomsApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module(){
		return new Hibernate5Module();
	}


}
