package com.example.demo.controller;

import com.example.demo.config.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private LimitConfig limitConfig;

    /*@Value("${minMoney}")
    private BigDecimal minMoney;

    @Value("${description}")
    private String description;*/

    @GetMapping("/hello")
    public String SayHello() {
        return "最少钱：" + limitConfig.getMinMoney() + "最多钱：" + limitConfig.getMaxMoney();
    }
}
