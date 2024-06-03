import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../../models/user";
import {FormControl, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly userUrl:string
  searchOption:any;
  constructor( private http:HttpClient) {
    this.userUrl = "http://localhost:8080/api/cariocar"
    this.searchOption = []
  }

  public findAll():Observable<User[]>{
      return this.http.get<User[]>(this.userUrl);
  }

  /*public findAllThatContains(searchValue:string){
    return this.http.get<User[]>(this.userUrl + `/find`);
  }*/
  public save(user: ɵTypedOrUntyped<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }, ɵFormGroupValue<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }>, any>){
    return this.http.post<User>(this.userUrl + '/adduser', user);
  }
}
