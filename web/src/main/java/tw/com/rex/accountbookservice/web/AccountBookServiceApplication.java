package tw.com.rex.accountbookservice.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"tw.com.rex.accountbookservice.model.dao"})
public class AccountBookServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountBookServiceApplication.class, args);
    }

}
