package tn.esprit.examentv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class ExamenTvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenTvApplication.class, args);
    }

}
