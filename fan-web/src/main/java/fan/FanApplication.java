package fan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * System startup class
 *
 * @author Fan
 * @since 2024/3/28 15:39
 */
@SpringBootApplication
@EnableScheduling
public class FanApplication {
    public static void main(String[] args) {
        SpringApplication.run(FanApplication.class, args);
    }
}