/*
package lazyLoad;


import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.controller.lazyLoad.EmployeeController;
import com.example.demo.entity.lazyLoad.Employee;
import com.example.demo.entity.lazyLoad.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@WebAppConfiguration
public class ElementCollectionIntegrationTest {

    @Autowired
    private EmployeeController employeeController;

        @Before
        public void init() {
            Employee employee = new Employee();
            employee.setId(new Long(1));
            employee.setName("Fred");
            employee.setPhones(
                    Arrays.asList(new Phone("work", "+55", "99999-9999"), new Phone("home", "+55", "98888-8888")));
            employeeRepository.save(employee);
        }

        @After
        public void clean() {
            employeeRepository.remove(1);
        }

        @Test(expected = org.hibernate.LazyInitializationException.class)
        public void whenAccessLazyCollection_thenThrowLazyInitializationException() {
            Employee employee = employeeRepository.findById(new Long(1)).orElseThrow(() -> new RuntimeException("can not find id 1"));

            assertThat(employee.getPhones().size(), is(2));
        }
    }
*/
