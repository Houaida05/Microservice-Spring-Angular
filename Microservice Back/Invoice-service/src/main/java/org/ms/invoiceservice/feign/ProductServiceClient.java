package org.ms.invoiceservice.feign;

import org.ms.invoiceservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@FeignClient(name ="PRODUCT-SERVICE")
public interface ProductServiceClient {
    @GetMapping(path = "/products/{id}")
    Product findProductById(@PathVariable(name = "id") Long id);
    @GetMapping(path="/products")
    PagedModel<Product> getAllProducts();
    @PutMapping(path = "/products/{id}")
    void changeQuantity(@PathVariable(name = "id") Long id, @RequestBody Product product);


}