package org.ms.customerservice.repository;

import org.ms.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
