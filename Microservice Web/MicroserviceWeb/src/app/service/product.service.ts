import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Product} from "../shared/product";
import {Category} from "../shared/category";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl = environment.urlProduct;

  constructor(private http: HttpClient) {
  }

  ListResource(url:any): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${url}`);
  }

  getCategory(id: any): Observable<Category> {
    return this.http.get<Category>(`${this.baseUrl}/products/${id}/category`);
  }

  postData(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/products/new`, data)
  }

  DeleteProduct(id: any): Observable<any> {
    return this.http.delete<any>(this.baseUrl + '/products/' + id);
  }
   getProduct(id:any): Observable<any>{
    return this.http.get<any>(this.baseUrl + '/products/' + id);
  }
  soldout(): Observable<any>{
    return this.http.get<any>(this.baseUrl + '/products/soldout');
  }
  getProdCateg(): Observable<any>{
    return this.http.get<any>(this.baseUrl + '/products/categoryprod');
  }

  putData(id:any,data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/products/quantity/`+id, data)
  }
}




