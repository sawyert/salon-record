package uk.co.drumcoder.salon;

import org.hibernate.annotations.FlushModeType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class SalonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalonApplication.class, args);
    }

}
