import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Car} from "../../models/car";
import {FormControl, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class CarService {
  private readonly carUrl:string
  constructor( private http:HttpClient) {
    this.carUrl = "http://localhost:8080/api/cariocar/car"
  }

  public findAll():Observable<Car[]>{
    return this.http.get<Car[]>(this.carUrl);
  }

  public save(car: ɵTypedOrUntyped<{
    ownerCpf: FormControl<string | null>;
    plate: FormControl<bigint | null>;
    model: FormControl<string | null>;
    age: FormControl<number | null>
  }, ɵFormGroupValue<{
    ownerCpf: FormControl<string | null>;
    plate: FormControl<string | null>;
    model: FormControl<string | null>;
    age: FormControl<string | null>
  }>, any>){
    return this.http.post<Car>(this.carUrl + '/addcar', car);
  }
}
