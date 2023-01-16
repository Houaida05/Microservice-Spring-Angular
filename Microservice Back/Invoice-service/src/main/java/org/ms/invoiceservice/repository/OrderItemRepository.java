package org.ms.invoiceservice.repository;
import org.ms.invoiceservice.dto.TotalProductDTO;
import org.ms.invoiceservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import java.util.Collection;
import java.util.List;

@RepositoryRestController
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    Collection<OrderItem> findByInvoiceId(Long invoiceId);
    @Query("SELECT new org.ms.invoiceservice.dto.TotalProductDTO(O.productName, SUM (O.quantity) )  FROM OrderItem AS O GROUP BY O.productName ")
    List<TotalProductDTO> countTotalProductOrdered();

}
