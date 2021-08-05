package io.orangebeard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightAggregateApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightAggregate.class, args);
    }
}
