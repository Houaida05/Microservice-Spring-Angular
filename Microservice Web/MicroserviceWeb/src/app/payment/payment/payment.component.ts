import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentService} from "../../service/payment.service";
import {Customer} from "../../shared/customer";
import {Payment} from "../../shared/payment";
import {InvoiceService} from "../../service/invoice.service";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  payment : Payment=new Payment();
  invoice: any;
  currencies:  Array<any> = [];
  currency: any;
  result:any;
  // amount:any;

  constructor( private router: Router, private paymentService: PaymentService, private invoiceService : InvoiceService,private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.payment.invoiceId= this.route.snapshot.paramMap.get('id');
    this.getCurrencies();
    this.invoiceService.getInvoice(this.payment.invoiceId).subscribe(data=>this.invoice=data)

  }
    Pay(){
      this.paymentService.postData(this.payment).subscribe(data =>this.router.navigate(['/invoice']));
    }

Convert(){
    this.paymentService.convertCurrency("TND",this.currency,this.invoice.totalSum).subscribe(data=>{
      this.result=data.result;
      console.log(this.currency);
      console.log(data);
      console.log(this.invoice.totalSum);
    })

}
  getCurrencies() {
    this.paymentService.getCurrencies().subscribe((data: any) => {
   //   console.log(data.currencies);
      Object.keys(data.currencies).forEach(key => {
        var obj = data.currencies[key];
     //   console.log('key is ' + key);
     //   console.log('obj ' + obj);
        this.currencies.push({k: key, o: obj});
        })
      });
    //  console.log('array is ' + this.currencies.toString());
  }
}
