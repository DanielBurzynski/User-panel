package pl.spring.springtest.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CustomTaskScheduler {


    @Scheduled(fixedRate = 5000)
    public void doSomeStuff(){

        System.out.println("jakas operacja czas : " + LocalTime.now());

    }






}
