package com.example.demo.Repository;

import com.example.demo.entity.many2many.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
