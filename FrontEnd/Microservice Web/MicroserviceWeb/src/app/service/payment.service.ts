import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Customer} from "../shared/customer";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Payment} from "../shared/payment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  baseUrl= environment.urlPayment;

  constructor(private http: HttpClient) { }

  postData(data:any):Observable<Payment>{
    return this.http.post<any>(`${this.baseUrl}/payments/new`, data);
  }
  convertCurrency(from: any,to : any, amount:any):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/currency-converter/from/${from}/to/${to}/amount/${amount}`);
  }
  getCurrencies():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/devises`);
  }
  getPayment(id: any) {
    return this.http.get<any>(`${this.baseUrl}/paymentsByInvoice/${id}`);
  }
}
