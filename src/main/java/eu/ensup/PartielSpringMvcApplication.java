package eu.ensup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages= {"eu.ensup.service", "eu.ensup.controller"})

//@EntityScan(basePackages = {"eu.ensup.controller"})
public class PartielSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartielSpringMvcApplication.class, args);
	}

}
