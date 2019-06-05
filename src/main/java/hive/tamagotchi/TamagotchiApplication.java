package hive.tamagotchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TamagotchiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamagotchiApplication.class, args);
	}

}
