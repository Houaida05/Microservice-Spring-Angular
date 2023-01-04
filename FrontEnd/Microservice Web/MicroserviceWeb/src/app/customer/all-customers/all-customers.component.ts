import {Component, OnInit} from '@angular/core';
import {Product} from "../../shared/product";
import {ProductService} from "../../service/product.service";
import {CustomerService} from "../../service/customer.service";
import {Customer} from "../../shared/customer";

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrls: ['./all-customers.component.css']
})
export class AllCustomersComponent implements OnInit {
  customers?: Customer[];

  constructor(private customerService: CustomerService,
  ) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers() {
    this.customerService.ListCustomers().subscribe(data => {
      this.customers = data._embedded.customers;
      console.log(this.customers);
    });
  }
  deleteCustomer(id : any) {
    let v = confirm("Are you sûre you want to delete this record?");
    if (v == true) {
      return this.customerService.DeleteCustomer(id).subscribe(data => this.getCustomers());
    } else {
      return this.getCustomers();
    }
  }
}
