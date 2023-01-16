import { Component, OnInit } from '@angular/core';
import {InvoiceService} from "../service/invoice.service";
import {Router} from "@angular/router";
import {ProductService} from "../service/product.service";
import {Product} from "../shared/product";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  array: []= [];
  tmpArray: []= [];
  Products:Product[]=[];
  categoryProd:[]=[];

  constructor(private invoiceService : InvoiceService,private productService:ProductService
           ) { }
  ngOnInit(): void {
    this.invoiceService.getProductOrder().subscribe(data =>{ this.array = data; console.log(this.array)});
    this.invoiceService.GetRevenue().subscribe(data =>{ this.tmpArray = data; console.log(this.tmpArray)});
    this.productService.soldout().subscribe(data =>{ this.Products = data; console.log(this.Products)});
    this.productService.getProdCateg().subscribe(data =>{ this.categoryProd = data; console.log(this.categoryProd)});


  }

}
