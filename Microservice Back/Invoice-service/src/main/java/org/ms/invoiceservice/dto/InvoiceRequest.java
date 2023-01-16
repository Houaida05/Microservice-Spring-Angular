package org.ms.invoiceservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class InvoiceRequest {
    private Long customerId=null;
    private List<ProductItem> products=new ArrayList<>();
}

