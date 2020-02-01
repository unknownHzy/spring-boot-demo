package com.example.demo.Repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.many2many.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
