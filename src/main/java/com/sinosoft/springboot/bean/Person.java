package com.sinosoft.springboot.bean;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到JavaBean组件中
 * ConfigurationProperties：通知SpringBoot将本类中所有的属性和配置文件中相关的配置进行绑定；
 * prefix = "person"；配置文件中那个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能使用容器提供的功能
 *
 * @ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；
 */
//@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person {
    /**
     * <bean class = "Person">
     *     <property name="LastName" value="?"></property>
     * </bean>
     */

//    @Value("${person.last-name}")
//   @Email lastname必须是邮箱格式
//    @Email
    private String LastName;
    @Value("#{2+3}")
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object>  lists;
    private  Dog dog;

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "LastName='" + LastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
