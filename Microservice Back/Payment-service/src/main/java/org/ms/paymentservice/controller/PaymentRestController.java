package org.ms.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.ms.paymentservice.dto.PaymentRequest;
import org.ms.paymentservice.feign.InvoiceServiceClient;
import org.ms.paymentservice.model.Invoice;
import org.ms.paymentservice.model.Payment;
import org.ms.paymentservice.repository.PaymentRepository;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequiredArgsConstructor
public class PaymentRestController {
    private final RestTemplate restTemplate;
    private static String url = "https://api.apilayer.com/fixer/symbols";
    private static String apikey = "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v";
    private final PaymentRepository paymentRepository;
    private final InvoiceServiceClient invoiceServiceClient;

    @PostMapping(path = "/payments/new")
    public void newPayment(@RequestBody PaymentRequest paymentRequest) {
        Invoice invoice = invoiceServiceClient.getInvoice(paymentRequest.getInvoiceId());
        Payment payment = new Payment();
        payment.setPaymentDate(new Date());
        payment.setPaymentType(paymentRequest.getPaymentType());
        payment.setInvoice(invoice);
        payment.setInvoiceId(paymentRequest.getInvoiceId());
        invoice.setPaymentId(payment.getId());
        paymentRepository.save(payment);
        invoiceServiceClient.updateStatus(paymentRequest.getInvoiceId());
    }

    @GetMapping(path = "/currency-converter/from/{from}/to/{to}/amount/{amount}")
    public Object CurrencyConversionBeanconvertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable double amount)
    {
        Map<String, String> uriVariables=new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        uriVariables.put("amount", String.valueOf(amount));
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("apikey", "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v");
        headers.add(HttpHeaders.USER_AGENT, "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Object>responseEntity= new RestTemplate().exchange("https://api.apilayer.com/currency_data/convert?to={to}&from={from}&amount={amount}",
                HttpMethod.GET,entity,Object.class, uriVariables);
        Object response = responseEntity.getBody();
        return response;
    }

    @GetMapping("/devises")
//    public Object getDevises(){
//            Object devises= restTemplate.getForObject("https://api.exchangerate.host/symbols", Object.class);
//            return devises;
    public Object devises() {
        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
        uriVariables.add("apikey", "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v");
        uriVariables.add("User-Agent", "Application");
//      uriVariables.add("currencies", "TND");
//      uriVariables.add("source", "EUR");
//      uriVariables.add("format", "1");
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("https://api.apilayer.com/currency_data/list").queryParams(uriVariables).build();
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add(HttpHeaders.USER_AGENT, "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                uriComponents.toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        return response.getBody();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("user-agent", "Application");
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("apikey", "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        String reponse = restTemplate.exchange("https://api.apilayer.com/currency_data/list", HttpMethod.GET, entity, String.class).getBody();
//        return  reponse;
//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(reponse);
//        JSONObject json = new JSONObject(reponse);
//         = restTemplate.getForObject("https://api.apilayer.com/fixer/symbols" + "?apikey=" + "Xq3zwkbuRgS7dV6aIMrTfJH5qb9fo24v", Object[].class);

    }

    @GetMapping("/paymentsByInvoice/{id}")
    public Payment getPayment(@PathVariable Long id){
//        Invoice invoice= invoiceServiceClient.getInvoice(id);
//        Long paymentId=invoice.getPaymentId();
        Payment payment= paymentRepository.findPaymentByInvoiceId(id);
        return payment;
    }
}
