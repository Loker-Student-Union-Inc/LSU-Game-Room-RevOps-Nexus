package edu.csudh.lsu.revops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "edu.csudh.lsu.persistence.repository")
@EntityScan(basePackages = "edu.csudh.lsu.persistence.model")
@ComponentScan(basePackages = {
        "edu.csudh.lsu.revops",
        "edu.csudh.lsu.persistence.service",
        "edu.csudh.lsu.persistence.repository.gamesroom.activity"
})
public class LsuGameRoomRevOpsNexusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsuGameRoomRevOpsNexusApplication.class, args);
    }
}
