package com.example.demo.Repository;

import com.example.demo.entity.lazyLoad.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
