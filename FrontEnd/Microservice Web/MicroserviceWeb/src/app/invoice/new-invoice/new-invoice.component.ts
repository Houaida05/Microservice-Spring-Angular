import { Component, OnInit } from '@angular/core';
import {Category} from "../../shared/category";
import {Customer} from "../../shared/customer";
import {CustomerService} from "../../service/customer.service";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Product} from "../../shared/product";
import {ProductService} from "../../service/product.service";
import {InvoiceService} from "../../service/invoice.service";
import {Router} from "@angular/router";
import {InvoiceRequest} from "../../shared/invoice-request";
@Component({
  selector: 'app-new-invoice',
  templateUrl: './new-invoice.component.html',
  styleUrls: ['./new-invoice.component.css']
})
export class NewInvoiceComponent implements OnInit {
  customerList: Customer[] |undefined;
  productList: Product[] |undefined;
  notAvailable: Boolean= false;
  quantity:any;
  val? : Boolean;
  product?:Product;
  check: number=0;
  constructor(private customerService : CustomerService,
              private formBuilder: FormBuilder,
              private productService : ProductService,
              private invoiceService : InvoiceService,
              private router: Router
  ) { }
  ngOnInit(): void {
    this.getCustomers();
    this.getProducts()
    this.Addnewrow();
  }
  items!: FormArray;
  invoiceRequest: InvoiceRequest = new InvoiceRequest();
  form = this.formBuilder.group({
    customerId: '1',
    products: new FormArray([])
  });
  getControls():FormArray {
    return<FormArray> (this.form.get('products') );
  }
  getArray():Array<InvoiceRequest>{
    return <Array<InvoiceRequest>>(this.form.get('products')?.value)
  }
  private getCustomers() {
    this.customerService.ListCustomers().
    subscribe(data=>{this.customerList=data._embedded.customers; console.log(data);});
  }
  private getProducts() {
    this.productService.ListResource('/products').
    subscribe(data=>{this.productList=data._embedded.products; console.log(data);});
  }
  submitForm() {
    console.log(this.getArray())
    this.invoiceService.checkAvailable(this.getArray()).subscribe(data=>
    { console.log(data)
      if(data){
      this.invoiceRequest = Object.assign(this.invoiceRequest, this.form.value);
      console.log(this.form.get('products')?.value)
      this.invoiceService.postData(this.invoiceRequest).subscribe(
        data => this.router.navigate(['/invoice']));
    }
    else {
this.notAvailable=true;
    }

  });
  }

  Genrow(): FormGroup {
    return new FormGroup({
      id:new FormControl('1'),
      quantity:new FormControl(1)
    });
  }
  Addnewrow() {
    this.items = this.form.get("products") as FormArray;
    this.items.push(this.Genrow())
  }
  Removeitem(index:any){
    this.items = this.form.get("products") as FormArray;
    this.items.removeAt(index)
  }


  // private filterProducts() {
  //   this.productList?.forEach((element,index)=>{
  //     // @ts-ignore
  //     if(element.quantity<2)
  //       delete this.productList ? this.productList : [index];
  //   });
  //
  // }


}
