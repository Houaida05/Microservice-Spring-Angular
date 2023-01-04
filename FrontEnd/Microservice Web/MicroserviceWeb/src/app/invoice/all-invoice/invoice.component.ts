import { Component, OnInit } from '@angular/core';
import {InvoiceService} from "../../service/invoice.service";
import {Invoice} from "../../shared/invoice";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import {Router} from "@angular/router";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  invoices?: Invoice[];

  constructor(private invoiceService : InvoiceService,
              private router: Router) { }

  ngOnInit(): void {
    this.getInvoices()
  }
  getInvoices() {
    this.invoiceService.ListInvoices().subscribe(data => {
      this.invoices = data._embedded.invoices;
      console.log(this.invoices);
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
      PDF.save('angular-demo.pdf');
    });
  }

}
