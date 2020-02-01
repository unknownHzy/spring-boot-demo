package com.example.demo.controller.many2many;

import com.example.demo.Repository.HobbyRepository;
import com.example.demo.Repository.PersonRepository;
import com.example.demo.entity.many2many.Hobby;
import com.example.demo.entity.many2many.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private HobbyRepository hobbyRepository; // TODO 这个应该放在service中

    /**
     * 创建人
     * @param name
     * @return
     */
    //TODO : 如果创建参数很多怎么办？必须以一个一个的RequestParam传递？？
    @PostMapping()
    public Person create(@RequestParam("name") String name) {
        Person person = new Person();
        person.setName(name);
        return personRepository.save(person);
    }

    /**
     * 查询所有人
     * @return
     */
    @GetMapping()
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    /**
     * 多对多，没有中间表实体例子
     * @param id
     * @param body
     */
    @PutMapping("/{id}/hobbies")
    public void chooseHobbies(@PathVariable("id") Long id, @RequestBody() Map<String, Long[]> body) { // body 是linkedHashMap
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(id)));
        Long [] hobbyIds = body.get("hobbyIds");
        // 查找出所有的hobby
        Hobby hobby;
        for (int i = 0; i < hobbyIds.length; i++) {
            long hobbyId = hobbyIds[i];
            hobby = hobbyRepository.findById(hobbyIds[i]).orElseThrow(() -> new RuntimeException("该兴趣id不存在" + hobbyId));
            person.getHobbies().add(hobby);
        }
        // 更新 person（添加hobbies） 会往中间表person_hobby中插入
        personRepository.save(person);
    }

    /**
     * 批量插入人
     * @return
     *  虽然说这个时候是批量插入的，但是实际上是一个一个插入的
     * Hibernate: insert into person (name) values (?)
     * Hibernate: insert into person (name) values (?)
     * Hibernate: insert into person (name) values (?)
     * Hibernate: insert into person (name) values (?)
     *
     * 那如何保证批量插入是一起的呢？
     */
    @PostMapping("/batches")
    public List<Person> insertByBatches() {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();
        Person p5 = new Person();
        Person p6 = new Person();
        Person p7 = new Person();
        p1.setName("批量插入人1");
        p2.setName("批量插入人2");
        p3.setName("批量插入人3");
        p4.setName("批量插入人4");
        p5.setName("批量插入人5");
        p6.setName("批量插入人6");
        p7.setName("批量插入人7");
        return personRepository.saveAll(Arrays.asList(p1, p2));
    }
}
