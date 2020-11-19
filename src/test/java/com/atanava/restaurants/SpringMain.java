package com.atanava.restaurants;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml"
                , "spring/spring-db.xml")) {
//            CrudMenuRepository repository = appCtx.getBean(CrudMenuRepository.class);
//            List<Menu> menus = repository.findAll();
//            menus.forEach(System.out::println);
        }
    }
}
