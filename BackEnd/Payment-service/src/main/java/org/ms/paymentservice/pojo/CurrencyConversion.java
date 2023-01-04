package org.ms.paymentservice.pojo;

import lombok.Data;

@Data
public class CurrencyConversion {
    private String date;
    private Boolean historical;
    private Info info;
    private Query query;
    private Double result;
    private Boolean success;

    public CurrencyConversion(Double result, Query query) {
        this.result=result;
        this.query=query;
    }

    // private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @Data
 class Info {

       private Double quote;
       private Integer timestamp;
   }
   @Data
   class Query{
       private Integer amount;
       private String from;
       private String to;
   }
}
