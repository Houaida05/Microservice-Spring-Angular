package org.ms.invoiceservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private long quantity;
    private double price;
    @Transient
    private Product product;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade={CascadeType.ALL})
    private Invoice invoice;

}
