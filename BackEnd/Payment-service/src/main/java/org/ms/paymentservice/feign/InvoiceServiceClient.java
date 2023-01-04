package org.ms.paymentservice.feign;

import feign.Param;
import org.ms.paymentservice.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name ="INVOICE-SERVICE")
public interface InvoiceServiceClient {
    @GetMapping(path = "/invoices/full-invoice/{id}")
    Invoice getInvoice(@PathVariable(name = "id") Long id);
    @PutMapping(path = "/invoices/{invoiceId}")
    void updateStatus(@PathVariable("invoiceId") Long invoiceId);
}
