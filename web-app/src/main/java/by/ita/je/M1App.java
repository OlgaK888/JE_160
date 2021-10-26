package by.ita.je;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.ita.je")
public class M1App {

    public static void main(String[] args) {
        SpringApplication.run(M1App.class, args);

    }
}
