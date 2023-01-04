package org.ms.productservice.repository;

import org.ms.productservice.model.Category;
import org.ms.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
public interface ProductRepository extends JpaRepository<Product,Long> {

}
