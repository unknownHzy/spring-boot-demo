package com.example.demo.entity.many2many;

import javax.persistence.*;
import java.util.Set;

/**
 * 多对多
 */
@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * 这里的mappedBy对应的是Person entity中的哪个字段
     */
    @ManyToMany(mappedBy = "hobbies")
    private Set<Person> persons;

    // additional properties
    // standard constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
