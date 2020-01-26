package com.example.demo.controller;

import com.example.demo.config.LimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // 而不能在这里加@RestController("prefix")
@RequestMapping("prefix") // http://localhost:8081/luckymoney/prefix/hello
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

    @GetMapping("/getId/{id}") // http://localhost:8081/luckymoney/prefix/getId/100
    public String getId(@PathVariable("id") Integer id) {
        return "path id: " + id;
    }

    @GetMapping("/query/string") // http://localhost:8081/luckymoney/prefix/query/string?id=100
    public String getQueryString(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id ) {
        return "query string: " + id;
    }

    @PostMapping("/post") // POST http://localhost:8081/luckymoney/prefix/post
    public String post(@RequestBody String body) {
        return body;
    }




}
