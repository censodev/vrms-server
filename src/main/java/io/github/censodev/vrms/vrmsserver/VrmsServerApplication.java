package io.github.censodev.vrms.vrmsserver;

import io.github.censodev.vrms.vrmsserver.data.seeders.Seeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class VrmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VrmsServerApplication.class, args);
        new Seeder().run();
    }

}
