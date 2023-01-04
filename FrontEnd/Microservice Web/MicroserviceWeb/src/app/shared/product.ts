import {Category} from "./category";

export class Product {
  id:any;
  name:string | undefined;
  description: string | undefined;
  price: number | undefined;
  quantity: number | undefined;
  image: Blob | undefined;
  category: Category = new Category();

}
