### 多对多的实现方法   
From: https://www.baeldung.com/jpa-many-to-many   
- [ ] A: 用@manyToMany注解不生成中间表：适用于中间表中不用存额外属性，只要存两个id的情况
- [ ] B: 用两个@manyToOne注解实现多对多（中间表实体需要用到注解@Embeddable并且实现Serializable)：  
中间表的主键是两个外键的联合主键，存在额外属性。适用于多个实体之间多对多。==联合主键的存在说明多对多之间只会出现一种情况==
```
 The simple many-to-many solution creates a relationship between two entities.
 Therefore, we cannot expand the relationship to more entities. 
 However, in this solution we don't have this limit: we can model relationships between any number of entity types.
```
- [ ] C: 用两个@ManyToOne跟两个@OneToMany实现：主键为自动生成的唯一键。不存在联合主键，因为两个实体之间的相同id会出现多次。   
可以存在多个额外属性。 适用于两个实体之间多对多，中间表中存在额外属性。
 ```
请注意，我们可以使用此解决方案来解决先前的问题：学生对课程进行评分。但是，除非必须这样做，否则创建专用的主键感觉很奇怪。
而且，从RDBMS的角度来看，这没有多大意义，因为将两个外键组合在一起就构成了完美的复合键。
此外，该组合键具有明确的含义：我们在关系中连接了哪些实体。
否则，B与C这两种实现之间的选择通常只是个人喜好。
```
```java
// 学生课程注册中间表实体
@Entity 
class CourseRegistration {
 
    @Id
    Long id;
 
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
 
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
 
    LocalDateTime registeredAt;
 
    int grade;
     
    // additional properties
    // standard constructors, getters, and setters
}
// 学生类
class Student {
 
    // ...
 
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;
 
    // ...
}
// 课程类
class Course {
 
    // ...
 
    @OneToMany(mappedBy = "courses")
    Set<CourseRegistration> registrations;
 
    // ...
}

```