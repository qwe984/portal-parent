package com.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.portal.cmc","com.portal.goods"})
public class INDEXApp {

    public static void main(String[] args) {
        SpringApplication.run(INDEXApp.class, args);
    }
}