package com.example.demo.controller;

import com.example.demo.Repository.CourseRepository;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository repository;

    @GetMapping()
    public List<Course> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Course updateById(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("wtf") String wtf, @RequestParam("total") Integer total) {
        Course course = repository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        course.setName(name);
        course.setWtf(wtf);
        course.setTotal(total);
        return repository.save(course);
    }

    @PostMapping()
    public Course create(@RequestParam("name") String name, @RequestParam("wtf") String wtf) {
        Course course = new Course();
        course.setName(name);
        course.setWtf(wtf);
        return repository.save(course);
    }

}
