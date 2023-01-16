package org.ms.invoiceservice.repository;

import feign.Param;
import org.ms.invoiceservice.dto.TotalProductDTO;
import org.ms.invoiceservice.dto.TotalRevenueCustomer;
import org.ms.invoiceservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestController
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Modifying
    @Query("UPDATE Invoice i SET i.status =true WHERE i.id = :invoiceId")
    void updateStatus(@Param("invoiceId") Long invoiceId);
    @Query("Select sum (i.totalSum) from Invoice i where i.customerId=:id and YEAR(i.invoiceDate)=:year and i.status=true")
    Double getSalesRevenueByYear(@Param("id") Long id, @Param("year") int year);
    @Query("Select sum (i.totalSum) from Invoice i where i.customerId=:id and i.status=true ")
    Double getSalesRevenue(@Param("id") Long id);

    @Query("Select sum (i.totalSum) from Invoice i where i.customerId=:id and i.status=false ")
    Double getRestUnpaid(@Param("id") Long id);

    List<Invoice> getInvoicesByCustomerIdAndStatus( Long customerId, Boolean status);

    @Query("SELECT new org.ms.invoiceservice.dto.TotalRevenueCustomer(i.customerName, SUM (i.totalSum) )  FROM Invoice AS i where i.status=true GROUP BY i.customerId ")
    List<TotalRevenueCustomer> countTotalrevenue();
}

