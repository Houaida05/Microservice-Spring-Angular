package org.ms.invoiceservice;

import org.ms.invoiceservice.model.Invoice;
import org.ms.invoiceservice.repository.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients
public class InvoiceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceRepository invoiceRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        repositoryRestConfiguration.exposeIdsFor(Invoice.class);
        return args -> {
        };
    }
}
//    @Bean
//    CommandLineRunner start(InvoiceRepository invoiceRepository,
//                            OrderItemRepository orderItemRepository,
//                            CustomerServiceClient customerServiceClient,
//                            ProductServiceClient productServiceClient) {
//        return args -> {
//            Customer customer = customerServiceClient.findCustomerById(1L);
//            Invoice invoice = invoiceRepository.save(new Invoice(null, new Date(), null, null, customer, customer.getId()));
//            PagedModel<Product> listeProduits = productServiceClient.getAllProducts();
//            listeProduits.forEach(p ->
//            {
//                OrderItem order = new OrderItem();
//                order.setProductId(p.getId());
//                order.setPrice(p.getPrice());
//                order.setQuantity(1 + new Random().nextInt(100));
//                order.setInvoice(invoice);
//                orderItemRepository.save(order);
//            });
//        };
//    }
//}
