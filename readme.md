# 二、Spring Boot 配置

## 1.配置文件

SpringBoot使用一个全局的配置文件,配置文件的名字是固定的

- application.properties

- application.yml

  

  配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好

  YAML(YAML Ain't Markup Language)

  ​	是一种标记语言

  ​	不是一种标记语言

  标记语言：

  ​	以前的配置文件；大多数使用xxx.xml文件；

  YAML：以数据为中心比json和xml更适合做配置文件

  YAML配置实例

  ```yaml
  server:
    port: 8082
  ```

  ![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/EE391866958F4F4CB42E8C3F1F90EB19/16497)

  XML配置实例

  ```xml
  <server>
  	<port>8082</port>
  </server>
  ```

  

  

  

    

## 2.YAML语法

### 1.基本语法

k:空格 v：表示一对键值对(空格必须有)

以**空格**的缩进来控制层级关系；只要是左对齐的一列数据，都是同一层级的

```yml
server:
	port: 8081
	path: /hello
	
```

属性和值也是大小写敏感的

### 2.值的写法

#### 字面量：普通的值(数字，字符串，布尔)

k: v : 字面直接来写；

​	字符串默认不用加上单引号或双引号；

​	"":双引号；不会转义字符串里的面的特殊字符；特殊字符会作为本身想表达的意思

​	name:  "zhangsan \n lisi" : 输出：zhangsan 换行 lisi

'': 单引号; 会转义特殊字符，特殊字符最终只是一个普通的字符串数据

​	name:  'zhangsan \n lisi' : 输出; zhangsan \n lisi'

​                  

#### 对象、Map(属性和值)(键值对)：

k: v : 在下一行来写对象的属性和值的关系；注意缩进

​	对象还是k:v的方式

```yaml
friends:

		lastName: zhangsan

		age: 20


```

行内写法

```yaml
friends: {lastName: zhangsan,age: 18}
```



#### 数组(List、Set)：

用- 值表示数组中的一个元素

```yaml
pets:
 - cat
 - dog
 - pig
```

行内用法

```yaml
pets: [cat,dog,pig]
```

## 3.配置文件值注入

配置文件

```application.yml``

```yaml
person:
   lastName: zz
   age: 22
   boss: false
   birth: 2019/4/18
   maps: {k1: v1,k2: v2}
   lists:
     - ss
     - jj
   dog:
     name: 花花
     age: 2

```



JavaBean

```java
/**
 * 将配置文件中配置的每一个属性的值，映射到JavaBean组件中
 * ConfigurationProperties：通知SpringBoot将本类中所有的属性和配置文件中相关的配置进行绑定；
 * prefix = "person"；配置文件中那个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能使用容器提供的功能
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String LastName;
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

```

导入配置文件处理器，以后编写配置文件就有提示

```xml
<!--导入配置文件处理器，配置文件进行绑定-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/BE7CE8BD15C24D999154DFC27CBE5E9B/16500)

### 1、properties配置文件在idea中默认utf-8可能会乱码

![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/0EB927A71B2E4E1987FAD1E7377C0848/16506)

### 2、@Value获取值和@ConfigurationProperties获取值比较

|                | @ConfigurationProterties | @Value     |
| -------------- | ------------------------ | ---------- |
| 功能           | 批量注入配置文件中的属性 | 一个个指定 |
| 松散语法绑定   | 支持                     | 不支持     |
| spEL           | 不支持                   | 支持       |
| JSR303数据校验 | 支持                     | 不支持     |
| 复杂类型封装   | 支持                     | 不支持     |

![数据校验](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/B4C9297A4A214D8B8ABAF55139EEAFC4/16514)

![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/3E7A2FFACD1446688A56F5BCC33C1382/16512)

配置文件yml还是properties都能获取到值

如果说，只在业务逻辑中需**要获取一下配置文件中的某项值，使用@Value；**

**如果说，专门编写了一个JavaBean来和配置文件进行映射，就直接使用@ConfigurationProperties**

### 3、配置文件注入值数据校验

必须用```@ConfigurationProperties```



```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {

    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></property>
     * <bean/>
     */

   //lastName必须是邮箱格式
    @Email
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    //@Value("true")
    private Boolean boss;

    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```

### 4、 @PropertySource&@ImportResource

@**PropertySource**: **加载指定的配置文件**；

```java
@PropertySource(value = {"classpath:person.properties"})
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
```

指定的配置文件

```properties
person.last-name=二狗子
person.age=17
person.birth=2019/9/9
person.boss=false
person.maps.k1=v1
person.maps..k2=v2
person.lists=a,b,c
person.dog.name=ww
person.dog.age=11
```

![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/D73436EDD99C4D64949464CC80BE90E2/16532)

@**ImportResource:**导入Spring的配置文件，让配置文件里的内容生效；

SpringBoot里面没有Spring的配置文件，自己编写的配置文件，也不能自动识别；

若想让Spring的配置文件生效，加载进来；@**ImportResource**:需要标注在一个配置类上

```java
//导入Spring的配置文件使其生效
@ImportResource(locations = {"classpath:beans.xml"})

```

不编写配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="helloService" class="com.sinosoft.springboot.service.HelloService"></bean>
</beans>
```



SpringBoot推荐给容器中添加组件的方式；SpringBoot推荐使用全注解的方式

1.配置类============Spring配置 文件

2.使用@Bean给容器中添加组件

MyAppConfig

```java
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


    }
}

```

测试类

```java
    @Test
    public void helloServiceTest(){

        Boolean reuult = ioc.containsBean("helloService2");
        System.out.println(reuult);
    }
```



![](https://note.youdao.com/yws/public/resource/855f0d021135a40a5523c8dc81729461/xmlnote/39A1F99EB8D445EFBD0A088F4A86C02E/16538)

