package com.example.demo.entity.many2many;

import javax.persistence.*;
import java.util.Set;

/**
 * 多对多: 使用ManyToMany不会产生中间表
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) 这个id生成策略会自动屏蔽批量操作
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "person_hobby",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private Set<Hobby> hobbies;

    // additional properties
    // standard constructors, getters, and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
