package org.ms.productservice;

import org.ms.productservice.model.Category;
import org.ms.productservice.model.Product;
import org.ms.productservice.repository.CategoryRepository;
import org.ms.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.DecimalFormat;
import java.util.Random;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);
        return args -> {
            categoryRepository.save(new Category(null, "Pcs and tablets"));
            categoryRepository.save(new Category(null, "Hardware and components"));
            categoryRepository.save(new Category(null, "Accessories and printers"));
            categoryRepository.save(new Category(null, "Smartphones"));
            productRepository.save(new Product(null,"Mouse","Mouse",15L,100,"souris.jpg",categoryRepository.getById(3l)));
            productRepository.save(new Product(null,"Iphone","Iphone",2500L,25,"iphone.png",categoryRepository.getById(4l)));
            productRepository.save(new Product(null,"Laptop","Laptop",2000L,20,"laptop.jpg",categoryRepository.getById(1l)));


//            categoryRepository.findAll().forEach(category -> {
//                Random index= new Random();
//                productRepository.save(new Product(null, "item"+ index.nextInt(100-1)+1  , null, Math.round(index.nextDouble()*1000) , index.nextInt(50-3)+3, "unknown.png",category));
//                productRepository.save(new Product(null, "item"+ index.nextInt(100-1)+1  , null, Math.round(index.nextDouble()*1000) , index.nextInt(50-3)+3, "unknown.png",category));
//                productRepository.save(new Product(null, "item"+ index.nextInt(100-1)+1  , null, Math.round(index.nextDouble()*1000) , index.nextInt(50-3)+3, "unknown.png",category));
//                    }
//            );
        };
    }
}

