import { Component, OnInit } from '@angular/core';
import {Customer} from "../../shared/customer";
import {CustomerService} from "../../service/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customer : Customer=new Customer();

  constructor(private customerService:CustomerService, private router: Router) { }

  ngOnInit(): void {
  }
  addCustomer(){
    this.customerService.postData(this.customer).subscribe(data =>this.router.navigate(['/customer']));
  }

}
