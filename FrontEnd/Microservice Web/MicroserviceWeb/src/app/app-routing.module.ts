import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes , RouterModule} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {AllProductsComponent} from "./product/all-products/all-products.component";
import {AddProductComponent} from "./product/add-product/add-product.component";
import {AllCustomersComponent} from "./customer/all-customers/all-customers.component";
import {AddCustomerComponent} from "./customer/add-customer/add-customer.component";
import {InvoiceComponent} from "./invoice/all-invoice/invoice.component";
import {NewInvoiceComponent} from "./invoice/new-invoice/new-invoice.component";
import {InvoiceDetailsComponent} from "./invoice/invoice-details/invoice-details.component";
import {PaymentComponent} from "./payment/payment/payment.component";

const routes: Routes = [
  {path : 'home', component: HomeComponent},
  {path: 'product/:p1/:p2', component: AllProductsComponent},
  {path:'product', redirectTo:'product/1/0', pathMatch:'full'},
  {path: 'addProduct', component: AddProductComponent},
  {path: 'customer', component: AllCustomersComponent},
  {path: 'addCustomer', component: AddCustomerComponent},
  {path: 'invoice', component: InvoiceComponent},
  {path: 'newInvoice', component: NewInvoiceComponent},
  {path: 'viewinvoice/:id', component: InvoiceDetailsComponent},
  {path: 'payment/:id', component: PaymentComponent},



];
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
