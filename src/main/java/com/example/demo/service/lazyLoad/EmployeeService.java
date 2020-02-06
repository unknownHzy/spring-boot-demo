package com.example.demo.service.lazyLoad;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entity.lazyLoad.Employee;
import com.example.demo.entity.lazyLoad.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee assembleEmployee(Employee body) {
        List<LinkedHashMap> phoneList = body.getPhones();
        List phones = new ArrayList<Phone>(phoneList.size());
        Phone phone;
        for (int i = 0; i < phoneList.size(); i++) { //TODO 这里就不能直接映射成Phone实体么？里面居然是一个LinkedHashMap
            phone = new Phone();
            Map p = phoneList.get(i);
            String areaCode = (String) p.get("areaCode");
            String number = (String) p.get("number");
            String type = (String) p.get("type");
            phone.setAreaCode(areaCode);
            phone.setNumber(number);
            phone.setType(type);
            phones.add(phone);
        }
        body.setPhones(phones);
        return body;
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Phone> findEmployeePhones(Long id) {
        Employee employee = this.findById(id);
        if (employee == null) {
            return null;
        }
        return employee.getPhones();
    }
}
