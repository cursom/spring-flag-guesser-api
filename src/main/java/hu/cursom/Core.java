package hu.cursom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "hu.cursom")
public class Core {
	public static void main(String[] args) {
		SpringApplication.run(Core.class, args);
	}
}