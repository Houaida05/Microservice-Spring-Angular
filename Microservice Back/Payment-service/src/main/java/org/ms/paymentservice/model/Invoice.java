package org.ms.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date invoiceDate;
    private Double totalSum;
    private boolean status;
    @Transient
    private Long paymentId;


}