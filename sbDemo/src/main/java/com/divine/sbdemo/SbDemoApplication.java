package com.divine.sbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class SbDemoApplication {
    public static void main(String[] args) {
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println("springVersion:" + springVersion + ";springBootVersion:" + springBootVersion);
        SpringApplication.run(SbDemoApplication.class, args);
    }

}
