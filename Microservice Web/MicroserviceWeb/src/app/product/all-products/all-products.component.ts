import {Component, Directive, EventEmitter, OnInit, Output} from '@angular/core';
import {Product} from 'src/app/shared/product';
import {ProductService} from "../../service/product.service";
import {Category} from "../../shared/category";
import {empty} from "rxjs";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {
  products?: Product[];
  currentcategory?: Category;
  result: any;
  categories?: Category[];
  baseurl?: String

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) {

  }

  ngOnInit(): void {
    this.baseurl = this.productService.baseUrl;
    //this.getProducts(url);
    this.getCategories('/categories');
    this.router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        let url = val.url;
        console.log(url);
        // console.log(this.activatedRoute.snapshot.data['asdf']);
        console.log(this.route.snapshot.params['p1']);
        let p1 = this.route.snapshot.params['p1'];
        if (p1 == 1) {
          this.getProducts('/products')
        } else if (p1 == 2) {
          let idcategory = this.route.snapshot.params['p2'];
          this.getProducts('/categories/' + idcategory + '/products');
        }
        if (url == "/product") {
          this.currentcategory = undefined;
        }
      }
    });
    let p1 = this.route.snapshot.params['p1'];
    if (p1 == 1) {
      this.getProducts('/products')
    }
  }

  getProducts(url: any) {
    this.productService.ListResource(url).subscribe(data => {
      this.products = data._embedded.products;
      console.log(this.products);
      this.getCat();
    });
  }

  getCat() {
    this.products?.forEach(p => {
      this.productService.getCategory(p.id).subscribe(cat => {
        p.category = cat;
      })
    });
  }

  getCategories(url: any) {
    this.productService.ListResource(url).subscribe(data => {
      this.categories = data._embedded.categories;
      console.log(this.categories);
    });
  }

  deleteProduct(id: any) {
    let v = confirm("Are you sure you want to delete this record?");
    if (v == true) {
      return this.productService.DeleteProduct(id).subscribe(data => this.getProducts('/products'));
    } else {
      return this.getProducts('/products');
    }
  }

  getProductsByCat(c: Category) {
    this.currentcategory = c;
    this.router.navigateByUrl('/product/2/' + c.id);

  }

  updateProduct(id: any) {
   this.productService.putData(id,1).subscribe();
   this.getProducts('/products');

  }
}
