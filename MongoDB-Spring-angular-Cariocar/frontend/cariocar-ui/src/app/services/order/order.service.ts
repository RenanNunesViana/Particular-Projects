import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order} from "../../models/order";
import {Observable} from "rxjs";
import {FormControl, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly orderUrl = "http://localhost:8080/api/cariocar/order"
  constructor(private http:HttpClient) { }

  public findAll():Observable<Order[]>{
    return this.http.get<Order[]>(this.orderUrl)
  }

  public save(order: ɵTypedOrUntyped<{
    note: FormControl<string | null>;
    carPlate: FormControl<string | null>;
    quote: FormControl<string | null>;
    paymentMade: FormControl<string | null>;
    description: FormControl<string | null>;
    id: FormControl<bigint | null>;
    customerCpf: FormControl<string | null>;
    paymentStats: FormControl<string | null>
  }, ɵFormGroupValue<{
    note: FormControl<string | null>;
    carPlate: FormControl<string | null>;
    quote: FormControl<string | null>;
    paymentMade: FormControl<string | null>;
    description: FormControl<string | null>;
    id: FormControl<string | null>;
    customerCpf: FormControl<string | null>;
    paymentStats: FormControl<string | null>
  }>, any>){
    return this.http.post(this.orderUrl +"/register", order);
}
}
