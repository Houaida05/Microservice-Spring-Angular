import { Component, OnInit } from '@angular/core';
import {InvoiceService} from "../../service/invoice.service";
import {PaymentService} from "../../service/payment.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Invoice} from "../../shared/invoice";
import {CustomerService} from "../../service/customer.service";
import {Customer} from "../../shared/customer";

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {
  paidinvoices?: Invoice[];
  unpaidinvoices?: Invoice[];
  availableYears = [2020, 2021, 2022,2023]

  customer: Customer= new Customer();
  id:any;
  rest : number=0;
  year : number=2023;
  selection: number=0;
  revenue : number =0;



  constructor(private invoiceService : InvoiceService,private route:ActivatedRoute,private customerservice:CustomerService){}

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.customerservice.GetCustomer(this.id).subscribe(data=>{this.customer=data
    });
    this.invoiceService.getPaidInvoices(this.id).subscribe(data=>this.paidinvoices=data);
    this.invoiceService.getUnPaidInvoices(this.id).subscribe(data=>this.unpaidinvoices=data);
    this.invoiceService.GetRestByCustomer(this.id).subscribe(data=>this.rest=data);

  }

  submitForm() {
    console.log(this.year)
    this.invoiceService.GetRevenueByCustomer(this.id, this.year).subscribe(data => this.revenue = data);

  }
  }
