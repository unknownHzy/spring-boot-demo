package com.example.demo.controller.lazyLoad;

import com.example.demo.entity.lazyLoad.Employee;
import com.example.demo.entity.lazyLoad.Phone;
import com.example.demo.service.lazyLoad.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * body {"name": "joy", "phones": [{"number": "15715708214", "areaCode": "86", "phone": "mobile"}]}
     * @param employee
     * @return
     */
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        // TODO 校验？？
        return employeeService.assembleEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    /**
     * 这里会涉及到懒加载：这里才是重点。。。。
     * @param id
     * @return
     */
    @GetMapping("/{id}/phones")
    public List<Phone> getEmployeePhones(@PathVariable("id") Long id) {
        return employeeService.findEmployeePhones(id);
    }
}
