package org.ms.paymentservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
    public class CurrencyPojo {

        private Boolean success;
        private Map<String, String> symbols;
    }
