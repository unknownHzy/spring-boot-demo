package com.example.demo.controller.many2many;

import com.example.demo.Repository.HobbyRepository;
import com.example.demo.entity.many2many.Hobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {

    @Autowired
    public HobbyRepository hobbyRepository;

    /**
     * 用注解RequestBody的时候，需要将Hobby entity中的构造函数全部删除，否则会报错
     * 这个时候在postman中用的是json 格式，而不是x-www-form-urlencoded（对应@RequestParam注解）
     * @param hobby
     * @return
     */
    @PostMapping()
    public Hobby create(@RequestBody() Hobby hobby) {
        return hobbyRepository.save(hobby);
    }
}
