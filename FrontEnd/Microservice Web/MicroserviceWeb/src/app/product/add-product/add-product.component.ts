import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../../service/product.service";
import {Category} from "../../shared/category";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  uploadedImage: File | undefined;
  categoryList: Category[] |undefined;


  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private productService : ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCategories();
  }
  form = this.formBuilder.group({
    name: '',
    price: '',
    description:'',
    quantity:'',
    file:'',
    category:'1'
  });
  public uploadFile(event :any) {
    this.uploadedImage = event.target.files[0];
  }
  submitForm() {
    const formData = new FormData();
    formData.append('file', this.uploadedImage!,this.uploadedImage!.name);
    formData.append('name', this.form.get('name')?.value!);
    formData.append('description', this.form.get('description')?.value!);
    formData.append('price', this.form.get('price')?.value!);
    formData.append('quantity', this.form.get('quantity')?.value!);
    formData.append('category',this.form.get('category')?.value!);
    this.productService.postData(formData).subscribe(
      data =>this.router.navigate(['/product']));
  }
getCategories(){
    this.productService.ListResource('/categories').subscribe(data=>{this.categoryList=data._embedded.categories; console.log(data);});
}
}
