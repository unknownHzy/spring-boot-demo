# spring-boot-demo
 -[x] 增删改查   
 -[x] many2many: 实现了中间表的插入。   
 -[ ] one2many:    
 -[ ] many2one: 
 -[ ] 批量操作：批量操作的开启与批量操作是否是事务的？
  If our entities use GenerationType.IDENTITY identifier generator, 
  Hibernate will silently disable batch inserts/updates.
 -[x] 为什么批量操作会有优势：速度快，省时间，尽快释放内存。
 -[x] hibernate.jdbc.batch_versioned_data: 如果你想让你的JDBC驱动从executeBatch()返回正确的行计数,
 那么将此属性设为true(开启这个选项通常是安全的). 同时，Hibernate将为自动版本化的数据使用批量DML. 
 默认值为false. eg. true | false
 -[ ] 事务   
 -[ ] AOP: 日志系统   
 -[ ] RequestBody：实现传入entity的校验，实体类不能带有构造函数。
 -[ ] xxxRepository会有个flush方法，具体是做什么的
 -[x] @GenerateValue自动生成id策略
 ```
    JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO. 
    TABLE：使用一个特定的数据库表格来保存主键。 
    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。 
    IDENTITY：主键由数据库自动生成（主要是自动增长型） 这种策略会自动屏蔽批量操作
    AUTO：主键由程序控制。 
 ```
-[ ] Persistence Context:
-[x] 懒加载：https://www.baeldung.com/java-jpa-lazy-collections   
懒加载帮助我们避免加载不必要的数据，有时候我们在closed Persistence Context读取没有初始化的数据
```
默认情况下：JPA使用的懒加载策略的注解是@ElementCollection
```
 
 解决的问题：   
 1）开启hibernate.generate_statistics：
 在yml文件中查看hibernate的统计：   
 来自文章：https://www.baeldung.com/spring-data-jpa-batch-inserts   
 spring.jpa.properties.hibernate.generate_statistics=true   
 遇到的问题：因为当时在yml中写入的时候是没有任何输入提示的。我以为有问题，实际上是没有问题的。
 开启之后批量操作会有如下提示：
 ```
    Hibernate: insert into person (name) values (?) // 
    Hibernate: insert into person (name) values (?)
    Hibernate: insert into person (name) values (?)
    Hibernate: insert into person (name) values (?)
    2020-01-30 11:04:41.222  INFO 7254 --- [nio-8081-exec-1] i.StatisticalLoggingSessionEventListener : Session Metrics {
        1038638 nanoseconds spent acquiring 1 JDBC connections;
        0 nanoseconds spent releasing 0 JDBC connections;
        2933661 nanoseconds spent preparing 4 JDBC statements; // 
        2427909 nanoseconds spent executing 4 JDBC statements;
        0 nanoseconds spent executing 0 JDBC batches;
        0 nanoseconds spent performing 0 L2C puts;
        0 nanoseconds spent performing 0 L2C hits;
        0 nanoseconds spent performing 0 L2C misses;
        5628360 nanoseconds spent executing 1 flushes (flushing a total of 4 entities and 0 collections);
        0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
    }

    执行4个插入操作 => 进行了4次JDBC声明 => 执行0次JDBC的批量操作 => 说明批量操作没有开启
    => 说明默认没有开启批量操作
    开启批量操作：
    spring.jpa.properties.hibernate.jdbc.batch_size=4
    spring.jpa.properties.hibernate.order_inserts=true

    1532838 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    2117100 nanoseconds spent preparing 3 JDBC statements;
    1850606 nanoseconds spent executing 2 JDBC statements;
    2123725 nanoseconds spent executing 1 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    15718020 nanoseconds spent executing 1 flushes (flushing a total of 2 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
 ```
2）批量操作失败：
```
批量操作开启为：
    spring.jpa.properties.hibernate.jdbc.batch_size=2
    spring.jpa.properties.hibernate.order_inserts=true
但是personRepository.saveAll(Arrays.asList(p1, p2))失败了，原因是
将Person的id生成策略设置成了personRepository.saveAll(Arrays.asList(p1, p2));
在https://stackoverflow.com/questions/50694329/bulk-insert-with-spring-boot-and-spring-data-jpa-not-working
中找到原因说这个策略会自动屏蔽批量操作，所以我将id生成策略改成了@GeneratedValue(strategy = GenerationType.SEQUENCE)

```
 
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
