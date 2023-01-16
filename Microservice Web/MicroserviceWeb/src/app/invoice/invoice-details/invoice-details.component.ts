import { Component, OnInit } from '@angular/core';
import {Invoice} from "../../shared/invoice";
import {InvoiceService} from "../../service/invoice.service";
import {ActivatedRoute, Router} from "@angular/router";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import {Payment} from "../../shared/payment";
import {PaymentService} from "../../service/payment.service";

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {
  invoice : Invoice= new Invoice();
  payment: Payment=new Payment();
  id:any;

  constructor(private invoiceService : InvoiceService,private paymentService: PaymentService,private route:ActivatedRoute,private router:Router){}
  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.invoiceService.getInvoice(this.id).subscribe(data=>{this.invoice=data
    });
    this.paymentService.getPayment(this.id).subscribe(data=>{this.payment=data
    });
  }


  public openPDF(): void {
    let DATA: any = document.getElementById('htmlData');
    html2canvas(DATA).then((canvas) => {
      let fileWidth = 208;
      let fileHeight = (canvas.height * fileWidth) / canvas.width;
      const FILEURI = canvas.toDataURL('image/png');
      let PDF = new jsPDF('p', 'mm', 'a4');
      let position = 0;
      PDF.addImage(FILEURI, 'PNG', 0, position, fileWidth, fileHeight);
      PDF.save('Facture.pdf');
    });
  }
}
