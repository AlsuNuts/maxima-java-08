package org.example;

import org.example.config.SpringConfig;
import org.example.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ProductService service = context.getBean(ProductService.class);

        service.start();
    }
}
