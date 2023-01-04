import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AddProductComponent } from './product/add-product/add-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { ViewProductComponent } from './product/view-product/view-product.component';
import { AppRoutingModule } from './app-routing.module';
import { AllProductsComponent } from './product/all-products/all-products.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AllCustomersComponent } from './customer/all-customers/all-customers.component';
import { AddCustomerComponent } from './customer/add-customer/add-customer.component';
import {ActivatedRoute} from "@angular/router";
import { InvoiceComponent } from './invoice/all-invoice/invoice.component';
import { NewInvoiceComponent } from './invoice/new-invoice/new-invoice.component';
import { InvoiceDetailsComponent } from './invoice/invoice-details/invoice-details.component';
import { PaymentComponent } from './payment/payment/payment.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddProductComponent,
    EditProductComponent,
    ViewProductComponent,
    AllProductsComponent,
    AllCustomersComponent,
    AddCustomerComponent,
    InvoiceComponent,
    NewInvoiceComponent,
    InvoiceDetailsComponent,
    PaymentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
