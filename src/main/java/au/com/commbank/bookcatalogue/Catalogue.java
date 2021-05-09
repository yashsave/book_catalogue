package au.com.commbank.bookcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"au.com.commbank.bookcatalogue"})
@SpringBootConfiguration
@EnableAutoConfiguration
public class Catalogue {
	public static void main (String[] args) {
		SpringApplication.run(Catalogue.class, args);
	}
}
