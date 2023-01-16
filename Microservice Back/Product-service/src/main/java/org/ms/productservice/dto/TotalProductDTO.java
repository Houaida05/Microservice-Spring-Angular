package org.ms.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalProductDTO {
    private String name;
    private Long value;

    public TotalProductDTO(String name, Long value) {
        this.name = name;
        this.value = value;
    }

}