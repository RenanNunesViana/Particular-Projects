import {PaymentStats} from "./payment-stats";

export class Order {
  id!:number;
  description!:string;
  note!:string;
  checkin!:Date;
  checkout!:Date;
  customerCpf!:string;
  carPlate!:string;
  paymentStats!:PaymentStats;
  quote!:number;
  paymentMade!:number;
  outstanding!:number;
}
