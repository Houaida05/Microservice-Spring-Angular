import {InvoiceRequest} from "./invoice-request";
import {Customer} from "./customer";

export class Invoice {

  id:any;
  invoiceDate:Date | undefined;
  totalSum:any | undefined;
  status: boolean | undefined;
  orders: InvoiceRequest[] | undefined
  customer: Customer | undefined
  customerId: any| undefined
}
