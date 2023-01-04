package org.ms.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.ms.paymentservice.pojo.CurrencyPojo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class DeviseApiController {
//    private final RestTemplate restTemplate;
//    private static String url= "https://api.apilayer.com/fixer/symbols";
//    private static String apikey="Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v";
//    public ResponseEntity<List<CurrencyPojo>> getDevises(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("user-agent", "Application");
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("apikey", "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return  restTemplate.exchange("https://api.apilayer.com/fixer/symbols", HttpMethod.GET, entity,  new ParameterizedTypeReference<List<CurrencyPojo>>(){});
//
//
//
//    }
//}
