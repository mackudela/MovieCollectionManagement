package pl.polsl.moviecollectionmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MovieCollectionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCollectionManagementApplication.class, args);
    }

}
