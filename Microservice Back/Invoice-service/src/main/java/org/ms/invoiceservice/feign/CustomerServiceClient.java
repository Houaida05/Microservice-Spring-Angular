package org.ms.invoiceservice.feign;

import org.ms.invoiceservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name ="CUSTOMER-SERVICE")
public interface CustomerServiceClient {
        @GetMapping(path = "/customers/{id}")
        Customer findCustomerById(@PathVariable(name = "id") Long id);
    }


