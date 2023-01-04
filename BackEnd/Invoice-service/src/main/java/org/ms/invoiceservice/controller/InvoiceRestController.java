package org.ms.invoiceservice.controller;

import feign.Param;
import lombok.RequiredArgsConstructor;
import org.ms.invoiceservice.model.Customer;
import org.ms.invoiceservice.dto.InvoiceRequest;
import org.ms.invoiceservice.model.Product;
import org.ms.invoiceservice.dto.ProductItem;
import org.ms.invoiceservice.feign.CustomerServiceClient;
import org.ms.invoiceservice.feign.ProductServiceClient;
import org.ms.invoiceservice.model.Invoice;
import org.ms.invoiceservice.model.OrderItem;
import org.ms.invoiceservice.repository.InvoiceRepository;
import org.ms.invoiceservice.repository.OrderItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
@RestController
@RequiredArgsConstructor
public class InvoiceRestController {
    private final InvoiceRepository invoiceRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;

    @GetMapping(path = "/invoices/full-invoice/{id}")
    public Invoice getInvoice(@PathVariable(name = "id") Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        Customer customer = customerServiceClient.findCustomerById(invoice.getCustomerId());
        invoice.setCustomer(customer);
        invoice.getOrders().forEach(fl -> {
            Product product = productServiceClient.findProductById(fl.getProductId());
            fl.setProduct(product);
        });
        return invoice;
    }
    @PostMapping(path="/invoices/new")
    public void newInvoice(@RequestBody InvoiceRequest invoiceRequest){
        Customer customer = customerServiceClient.findCustomerById(invoiceRequest.getCustomerId());
        Invoice invoice = new Invoice();
        double sum= 0;
        for(ProductItem p : invoiceRequest.getProducts()) {
            OrderItem order = new OrderItem();
            Product product=productServiceClient.findProductById(p.getId());
            order.setProductId(product.getId());
            order.setPrice(product.getPrice());
            order.setQuantity(p.getQuantity());
            product.setQuantity((int) (product.getQuantity() - order.getQuantity()));
            order.setProduct(product);
            productServiceClient.changeQuantity(order.getProductId(),product);
            order.setInvoice(invoice);
            sum += product.getPrice() * p.getQuantity();
            orderItemRepository.save(order);
        }
            invoice.setTotalSum(sum);
            invoice.setCustomer(customer);
            invoice.setInvoiceDate(new Date());
            invoice.setCustomerId(customer.getId());
            invoiceRepository.save(invoice);

    }
    @RequestMapping(path = "/invoices/quantity", method = { RequestMethod.GET, RequestMethod.POST } ,headers = "Accept=application/json")
    public Boolean checkAvailable( @RequestBody ProductItem[] productItems) {
        return Arrays.stream(productItems).allMatch((ProductItem p)-> (
             this.productServiceClient.findProductById(p.getId()).getQuantity()> p.getQuantity()));
    }
        @PutMapping(path = "/invoices/{invoiceId}")
    public void updateStatus(@PathVariable("invoiceId") Long invoiceId)
    {
        invoiceRepository.updateStatus(invoiceId);
    }
}





