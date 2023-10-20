import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order} from "../../models/order";
import {BehaviorSubject, Observable} from "rxjs";
import {FormControl, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly orderUrl = "http://localhost:8080/api/cariocar/order"

  private orderToEditId$ = new BehaviorSubject<any>({})
  selectedOrderToEditId$ = this.orderToEditId$.asObservable()
  constructor(private http:HttpClient) { }

  public findAll():Observable<Order[]>{
    return this.http.get<Order[]>(this.orderUrl)
  }

  public findById(orderId:number){
    return this.http.get<Order>(this.orderUrl + `/${orderId}`)
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

  public edit(id: number, order: ɵTypedOrUntyped<{
    note: FormControl<string | null>;
    carPlate: FormControl<string | null>;
    quote: FormControl<string | null>;
    checkin: FormControl<string | null>;
    outstanding: FormControl<string | null>;
    paymentMade: FormControl<string | null>;
    description: FormControl<string | null>;
    id: FormControl<string | null>;
    customerCpf: FormControl<string | null>;
    checkout: FormControl<string | null>;
    paymentStats: FormControl<string | null>
  }, ɵFormGroupValue<{
    note: FormControl<string | null>;
    carPlate: FormControl<string | null>;
    quote: FormControl<string | null>;
    checkin: FormControl<string | null>;
    outstanding: FormControl<string | null>;
    paymentMade: FormControl<string | null>;
    description: FormControl<string | null>;
    id: FormControl<string | null>;
    customerCpf: FormControl<string | null>;
    checkout: FormControl<string | null>;
    paymentStats: FormControl<string | null>
  }>, any>){
    return this.http.put(this.orderUrl + `/${id}`, order)
  }

  public delete(id:number){
    return this.http.delete((this.orderUrl + `/delete/${id}`))
  }

  setOrderToEditId(id:bigint){
    this.orderToEditId$.next(id);
  }
}
