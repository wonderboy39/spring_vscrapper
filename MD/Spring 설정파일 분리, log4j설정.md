# 스프링 설정파일 분리, log4j 설정

  

## 스프링 설정파일 분리

ㅁㄴㅇㄹㅁㄴㅇㄹ  

**web.xml**  

```xml
<web-app>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>   org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
</web-app>
```

