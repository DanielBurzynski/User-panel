package pl.spring.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AplikacjaUserPanel {

    public static void main(String[] args) {
        SpringApplication.run(AplikacjaUserPanel.class, args);
    }

}
