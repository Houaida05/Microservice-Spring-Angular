package org.ms.paymentservice.dto;

import lombok.Data;

import java.util.Date;
@Data
public class PaymentRequest {
    private Long invoiceId;
    private Date paymentDate;
    private String paymentType;
}
