package com.sinosoft.springboot;

import com.sinosoft.springboot.bean.Person;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 *
 * 可以在测试期间，方便使用类似编码的方式自动注入到容器中
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {
    @Autowired
    Person person;
    @Autowired
    ApplicationContext ioc;


    @Test
    public void contextLoads() {
        System.out.println(person);
    }
    @Test
    public void helloServiceTest(){

        Boolean reuult = ioc.containsBean("helloService2");
        System.out.println(reuult);
    }


}
