import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../shared/customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  baseUrl= environment.urlCustomer;
  constructor(private http: HttpClient) { }
  ListCustomers():Observable<any>{
    return this.http.get<any>(this.baseUrl);
  }
  DeleteCustomer(id:any):Observable<any>{
    return this.http.delete<any>(this.baseUrl+'/'+id);
  }
  GetCustomer(id:any):Observable<any>{
    return this.http.get<Customer>(this.baseUrl+'/'+id);
  }
  postData(data:any):Observable<Customer>{
    return this.http.post<any>(this.baseUrl, data);
  }

}
