package org.ms.invoiceservice.repository;

import feign.Param;
import org.ms.invoiceservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestController
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Modifying
    @Query("UPDATE Invoice i SET i.status =true WHERE i.id = :invoiceId")
    void updateStatus(@Param("invoiceId") Long invoiceId);
}

