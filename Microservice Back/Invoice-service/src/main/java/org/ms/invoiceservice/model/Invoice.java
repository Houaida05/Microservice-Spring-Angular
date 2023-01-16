package org.ms.invoiceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date invoiceDate;
    private Double totalSum;
    private boolean status;
    @OneToMany(mappedBy = "invoice")
    private Collection<OrderItem> Orders;
    @Transient
    private Customer customer;
    private Long customerId;
    private String customerName;

}

