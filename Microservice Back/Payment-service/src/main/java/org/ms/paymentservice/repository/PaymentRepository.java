package org.ms.paymentservice.repository;

import org.ms.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findPaymentByInvoiceId(Long id);
}
