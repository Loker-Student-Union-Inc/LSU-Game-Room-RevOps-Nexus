package edu.csudh.lsu.revops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "edu.csudh.lsu.persistence.repository")
@EntityScan(basePackages = "edu.csudh.lsu.persistence.model")
public class LsuGameRoomRevOpsNexusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsuGameRoomRevOpsNexusApplication.class, args);
    }

}
