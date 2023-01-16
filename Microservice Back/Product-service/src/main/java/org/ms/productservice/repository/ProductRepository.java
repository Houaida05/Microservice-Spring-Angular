package org.ms.productservice.repository;

import org.ms.productservice.dto.TotalProductDTO;
import org.ms.productservice.model.Category;
import org.ms.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestController
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByQuantity(int quantity);
    @Query("SELECT new org.ms.productservice.dto.TotalProductDTO(p.category.name, COUNT(p.category.name)) FROM Product AS p GROUP BY p.category.name")
    List<TotalProductDTO> countTotalProductsByCategory();
}
