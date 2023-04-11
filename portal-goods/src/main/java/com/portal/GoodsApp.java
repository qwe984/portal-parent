package com.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.portal.cmc","com.portal.es"})
@MapperScan({"com.portal.goods.mapper"})
public class GoodsApp {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApp.class, args);
    }
}
