# Spring Boot Starter with aliyun OSS and Minio OSS

整合了阿里云和Minio的OSS SDK，为Spring Boot 项目提供可插拔的OSS对象存储服务功能。
个人博客：https://felord.cn

# API

抽象接口`cn.felord.oss.Storage`作为操作OSS的核心接口，目前提供了四种方法：

```java
package cn.felord.oss;

import java.io.InputStream;

/**
 * The interface Storage.
 *
 * @author felord.cn
 * @since 2020 /8/24 19:54
 */
public interface Storage {


    /**
     * 存放对象
     *
     * @param bucketName   bucket  名称
     * @param objectName  自定义对象名称
     * @param inputStream  对象的输入流
     * @param contentType  参考http 的 MimeType 值
     * @throws Exception the exception
     */
    void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;

    /**
     *  获取对象
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     * @return the object
     */
    InputStream getObject(String bucketName, String objectName) throws Exception;

    /**
     *  获取对象的URL
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     * @return the object url
     */
    String getObjectUrl(String bucketName, String objectName) throws Exception;

    /**
     *  删除对象
     *
     * @param bucketName the bucket name
     * @param objectName the object name
     */
    void removeObject(String bucketName, String objectName) throws Exception;

}
```

## 依赖安装
`git pull`之后，通过`mvn install`命令安装到本地Maven仓库，当然你也可以上传到你的Maven私仓。然后引入Maven坐标：
```xml
<!--  一定要先拉取项目通过 mvn install 安装到本地  -->
<dependency>
    <groupId>cn.felord</groupId>
    <artifactId>oss-spring-boot-starter</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```
## 配置生效条件

这里使用了Spring Boot的条件生效功能以达到灵活配置的目的，所以要认真阅读下面的说明。

### 只使用Minio的配置
`application.yaml`的配置
```yaml
oss:
  minio:
  # 启用 
    active: true  
    access-key: minio_access_key
    secret-key: felord_cn_sec_key
  # minio 地址  
    endpoint: http://localhost:9000
```
然后使用Spring Bean，引入的方式： 
```java
    @Autowired
    @Qualifier("minioStorage")
    Storage storage;
```
或者
```java
    @Autowired
    Storage minioStorage;
```

### 只使用阿里云OSS的配置
需要额外引入阿里云OSS SDK:
```xml
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>2.5.0</version>
</dependency>
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>aliyun-java-sdk-core</artifactId>
    <version>4.3.8</version>
</dependency>
```
`application.yaml`的配置：
```yaml
oss:
  aliyun:
    active: true
    access-key-id: LTAI4GH4EQXtKEbJDrADvWNH
    access-key-secret: XjDpNn5JqHAHPDXGL6xIebyUkyFAZ7
    endpoint: oss-cn-beijing.aliyuncs.com
```
然后使用Spring Bean，引入的方式： 
```java
    @Autowired
    @Qualifier("aliyunStorage")
    Storage storage;
```
或者
```java
    @Autowired
    Storage aliyunStorage;
```

### 同时使用

参考上面的两个配置，请注意这时Spring IoC存在两个`Storage`,需要显式指定使用哪个。

### 开关

你可以通过`oss.<minio|aliyun>.active`配置项来显式的进行开关对应的OSS.

## 其它

有问题可通过微信MSW_623联系或者提交ISSUE。