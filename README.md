# spring-boot-demo
spring-boot-demo

```
注解：
1. 整个spring boot程序的入口: @SpringBootApplication
2. yml的配置文件 -> 配置文件类LimitConfig：@ConfigurationProperties(prefix = "limit") @Component这个是必须的 否则无法依赖注入。
3. 在controller中注入配置文件类 采用@Autowired   
  @Autowired
  private LimitConfig limitConfig;
4. 如果要暴露yml配置中的单个字段，而不是以整个LimitConfig类暴露的话，在controller中直接使用
  @Value("${minMoney}")
  private BigDecimal minMoney
5. controller的注解： @RestController
6. get请求方法的注解(post请求方法的注解@PostMapping)：@GetMapping("/hello") 这是曾经的@RequestMapping()注解的简写方式
  localhost:8081/luckymoney/hello

请求路径说明：
// application-dev.yml
server:
  port: 8081 // 这是暴露的端口
  servlet:
    context-path: /luckymoney // localhost:8081/luckymoney

7. @Controller
8. @ResponseBody
9. @RestController 就是之前的@Controller + @ResponseBody 才能返回json
10. @RequestMapping：旧版本spring-boot注解。虽然部分可以用GetMapping代替，但是某些特殊的情况还是需要用到这个
 如果是
11. 获取url中的数据：@PathVariable
12. 获取请求参数的值：@RequestParam

```
