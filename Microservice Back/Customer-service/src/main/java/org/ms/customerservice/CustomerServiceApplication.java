package org.ms.customerservice;

import org.ms.customerservice.model.Customer;
import org.ms.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args ->
        {
            customerRepository.save(new Customer(null, "Ali","Ali", "ali.ms@gmail.com", "22222222"));
            customerRepository.save(new Customer(null, "Mariem", "Mariem","Mariem.ms@gmail.com", "55555555"));
            customerRepository.save(new Customer(null, "Mohamed","Mohamed", "Mohamed.ms@gmail.com", "44444444"));
            for (Customer customer : customerRepository.findAll()) {
                System.out.println(customer.toString());
            }
        };
    }
}

