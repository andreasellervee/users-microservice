package users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableJms
@EnableAsync
public class UsersMicroserviceApp {

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroserviceApp.class);
    }
}
