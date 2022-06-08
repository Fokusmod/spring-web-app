package ru.geekbrains.homework6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.homework6.config.SpringConfig;
import ru.geekbrains.homework6.model.Product;
import ru.geekbrains.homework6.service.Service;


public class Main {


    private static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {

        Service service = context.getBean(Service.class);



        service.getCartBuyers(1L);
        service.setPrice(1L);
        service.addOrder(1L, 1L);
        service.getCartBuyers(1L);
    }

}
