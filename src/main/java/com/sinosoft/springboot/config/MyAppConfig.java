package com.sinosoft.springboot.config;

import com.sinosoft.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration:指明当前类是一个配置类；替代之前的配置文件
 *
 * 在配置文件中<bean></bean>添加标签组件
 */
@Configuration
public class MyAppConfig {
//    将方法的返回值添加到容器中；容器中组件默认的id就是方法名
    @Bean
    public HelloService helloService2(){
        System.out.println("配置类@Bean给容器中添加组件了");
        return  new HelloService();
        //试试分支


    }
}
