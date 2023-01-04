package org.ms.invoiceservice.repository;

import org.ms.invoiceservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;

@RepositoryRestController
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Collection<OrderItem> findByInvoiceId(Long invoiceId);
}
