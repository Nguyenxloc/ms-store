package com.example.java4;
import com.example.java4.entities.KieuTay;
import com.example.java4.repositories.KieuTayRepository;
import com.example.java4.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Date;

@SpringBootApplication
public class WebSpringBootApp   {
 
    public static void   main(String[] args) {
        SpringApplication.run(WebSpringBootApp.class, args);


    }

}
