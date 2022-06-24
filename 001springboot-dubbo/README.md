# springboot-dubbo-nacos


## 配置 nacos
- 安装 [nacos](https://github.com/alibaba/nacos/releases) 下载 `nacos-server-$version.zip` 压缩包
- 解压缩，进入 `nacos -> bin` 目录，执行启动命令：
```sh
sh startup.sh -m standalone
```
- 浏览器打开 [nacos管理平台](http://127.0.0.1:8848/nacos/), 默认账号密码：nacos
- 关闭服务命令：
```sh
sh shutdown.sh
```


## 创建项目
基础工程
- pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.0</version>
    <relativePath/> <!-- lookup parent from repository -->
  </parent>

  <groupId>cn.com.wmy</groupId>
  <artifactId>springboot-dubbo</artifactId>
  <packaging>pom</packaging>
  <version>1.0.0-SNAPSHOT</version>

  <modules>
    <module>springboot-dubbo-api</module>
    <module>springboot-dubbo-provider</module>
    <module>springboot-dubbo-consumer</module>
  </modules>

  <properties>
    <java.version>1.8</java.version>
  </properties>

</project>
```


## 创建 dubbo-api
公共接口，实体类。
- pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <artifactId>springboot-dubbo</artifactId>
    <groupId>cn.com.wmy</groupId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>
  
  <artifactId>springboot-dubbo-api</artifactId>
  <packaging>pom</packaging>
  
  <dependencies>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
  </dependencies>
  
</project>
```
- pojo
```java
@Data
public class Brand implements Serializable {
	private static final long serialVersionUID = -4949803337939774464L;
	private Integer id;
	private String name;
	private String image;
	private String letter;
	private Integer seq;
}
```
- service
```java
public interface BrandService {
	List<Brand> findAll();
}
```


## 创建 dubbo-provider
服务提供者
- pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <artifactId>springboot-dubbo</artifactId>
    <groupId>cn.com.wmy</groupId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>

  <artifactId>springboot-dubbo-provider</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>jar</packaging>
  <name>springboot-dubbo-provider</name>
  <description>Demo project for Spring Boot</description>

  <properties>
    <java.version>1.8</java.version>
  </properties>

  <dependencies>
    <!-- api -->
    <dependency>
      <groupId>cn.com.wmy</groupId>
      <artifactId>springboot-dubbo-api</artifactId>
      <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <!-- spring-boot -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
    <!-- jdbc -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <scope>runtime</scope>
    </dependency>
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.2.2</version>
    </dependency>
    <!-- dubbo -->
    <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-spring-boot-starter</artifactId>
      <version>2.7.8</version>
    </dependency>
    <!-- dubbo 注册 nacos -->
    <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-registry-nacos</artifactId>
      <version>2.7.8</version>
      <exclusions>
        <exclusion>
          <groupId>commons-io</groupId>
          <artifactId>commons-io</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.7.0</version>
      </plugin>
    </plugins>
  </build>

</project>

```
- properties
```properties
########################################################################################################################
# http://localhost:8080/demo/api/
server.port=8080
server.servlet.context-path=/demo/api
########################################################################################################################
spring.profiles.active=dev
# jdbc
spring.datasource.username=root
spring.datasource.password=root1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/ys_goods?characterEncoding=UTF8&serviceTimezone=UTC&useSSL=false
########################################################################################################################
# dubbo
# 服务名称
dubbo.application.name=springboot-dubbo-provider
dubbo.scan.base-packages=cn.com.wmy.springbootdubboprovider.service.impl
# 注册服务地址
dubbo.registry.address=nacos://127.0.0.1:8848
# dubbo协议，固定写法
dubbo.protocol.name=dubbo
# 暴露服务端口（默认是20880，不同的服务提供者，端口不能重复）
dubbo.protocol.port=20880
########################################################################################################################
# mapper.xml 文件所在目录
mybatis.mapper-locations=classpath*:mapper/*.xml
# mapper.xml 文件中 resultMap 的 type、parameterType、resultType 所引用的实体类所在的包，不配置的话，引用实体类需要写全路径
mybatis.type-aliases-package=cn.com.my.springbootdubboprovider.entity
# 开启驼峰命名
mybatis.configuration.map-underscore-to-camel-case=true
########################################################################################################################
logging.level.root=debug
```
- application
```java
@EnableDubbo
@SpringBootApplication
public class SpringbootDubboProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboProviderApplication.class, args);
	}
}
```
- mapper
```java
@Mapper
public interface BrandMapper {
	List<Brand> findAll();
}
```
- resources-mapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.com.wmy.springbootdubboprovider.mapper.BrandMapper">
  <resultMap id="brandMap" type="cn.com.wmy.springbootdubboapi.pojo.Brand">
    <id column="id" jdbcType="INTEGER" property="id"/>
    <result column="name" jdbcType="VARCHAR" property="name"/>
    <result column="image" jdbcType="VARCHAR" property="image"/>
    <result column="letter" jdbcType="VARCHAR" property="letter"/>
    <result column="seq" jdbcType="INTEGER" property="seq"/>
  </resultMap>
  <select id="findAll" resultMap="brandMap">
    SELECT * FROM tb_brand
  </select>
</mapper>
```
- service.impl
```java
@DubboService(version = "1.0.0", group = "DUBBO")
public class BrandServiceImpl implements BrandService {
	@Resource
	private BrandMapper brandMapper;

	@Override
	public List<Brand> findAll() {
		return brandMapper.findAll();
	}
}

```
- test
```java
@SpringBootTest
public class BrandServiceTest {
	@Resource
	private BrandService brandService;

	@Test
	public void findAllTest() {
		List<Brand> result = brandService.findAll();
		System.out.println(result);
	}
}
```

## 创建 dubbo-consumer
服务消费者
- pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <artifactId>springboot-dubbo</artifactId>
    <groupId>cn.com.wmy</groupId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>

  <artifactId>springboot-dubbo-consumer</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <name>springboot-dubbo-consumer</name>
  <description>Demo project for Spring Boot</description>

  <properties>
    <java.version>1.8</java.version>
  </properties>

  <dependencies>
    <!-- dubbo-api -->
    <dependency>
      <groupId>cn.com.wmy</groupId>
      <artifactId>springboot-dubbo-api</artifactId>
      <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <!-- spring-boot -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
    <!-- dubbo -->
    <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-spring-boot-starter</artifactId>
      <version>2.7.8</version>
    </dependency>
    <!-- dubbo 注册 nacos -->
    <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-registry-nacos</artifactId>
      <version>2.7.8</version>
      <exclusions>
        <exclusion>
          <groupId>commons-io</groupId>
          <artifactId>commons-io</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.7.0</version>
      </plugin>
    </plugins>
  </build>

</project>
```
- properties
```properties
########################################################################################################################
# http://localhost:8081/demo/api/
server.port=8081
server.servlet.context-path=/demo/api
########################################################################################################################
spring.profiles.active=dev
########################################################################################################################
# dubbo
# 服务名称
dubbo.application.name=springboot-dubbo-consumer
# 注册服务地址
dubbo.registry.address=nacos://127.0.0.1:8848
# dubbo协议，固定写法
dubbo.protocol.name=dubbo
########################################################################################################################
logging.level.root=debug
```
- application 
```java
@EnableDubbo
@SpringBootApplication
public class SpringbootDubboConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboConsumerApplication.class, args);
	}
}
```
- controller
```java
@RestController
@RequestMapping("/brand")
public class BrandController {

	@DubboReference(version = "1.0.0", group = "DUBBO", interfaceClass = BrandService.class)
	private BrandService brandService;

	@RequestMapping("/findAll")
	public List<Brand> findAll() {
		return brandService.findAll();
	}
}
```
- test
```java
@SpringBootTest
public class BrandControllerTest {
	@Resource
	private BrandController brandController;

	@Test
	public void findAllTest() {
		List<Brand> result = brandController.findAll();
		System.out.println(result);
	}
}
```

## 配置 database
配置数据源
`Database` -> `+` -> `MySQL`
```text
Host: localhost
Port: 3306
User: root
Password: root1234
Database: #db_name#
URL: jdbc:mysql://localhost:3306/ys_goods?characterEncoding=UTF8&serviceTimezone=UTC&useSSL=false
```