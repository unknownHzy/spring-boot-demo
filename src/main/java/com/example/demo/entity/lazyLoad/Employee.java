package com.example.demo.entity.lazyLoad;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String name;

    @ElementCollection
    @CollectionTable(name = "employee_phones", joinColumns = @JoinColumn(name = "employee_id"))
    private List<Phone> phones;

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

    public List getPhones() {
        return phones;
    }

    public void setPhones(List phones) {
        this.phones = phones;
    }
}

