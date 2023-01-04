import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {InvoiceRequest} from "../shared/invoice-request";

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  baseUrl = environment.urlInvoice;

  constructor(private http: HttpClient) { }

  postData(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/new`, data)
  }
  ListInvoices():Observable<any>{
    return this.http.get<any>(this.baseUrl);
  }
  getInvoice(id :any):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/full-invoice/${id}`);
  }
  checkAvailable(orderTable: any[]):Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/quantity`,orderTable)
  }

}
