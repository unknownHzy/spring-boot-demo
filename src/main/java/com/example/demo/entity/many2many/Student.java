package com.example.demo.entity.many2many;

import javax.persistence.*;

/**
 * 学生：一个学生可以有好多个老师
 */
@Entity
@Table(name = "student")
public class Student {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
