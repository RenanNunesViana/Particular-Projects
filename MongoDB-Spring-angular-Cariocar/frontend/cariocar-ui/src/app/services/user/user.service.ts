import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../../models/user";
import {FormControl, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";
import {environment} from "../../../environments/environment.development";


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly userUrl:string

  private userToEditId$ = new BehaviorSubject<any>({});
  selectedUserId$ = this.userToEditId$.asObservable();
  constructor( private http:HttpClient) {
    this.userUrl = environment.link
  }

  public findAll():Observable<User[]>{
      return this.http.get<User[]>(this.userUrl);
  }

  public findUser(id:bigint):Observable<User>{
    return this.http.get<User>(this.userUrl + `/${id}`)
  }

  public save(user: ɵTypedOrUntyped<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    id: FormControl<number | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }, ɵFormGroupValue<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    id: FormControl<string | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }>, any>){
    return this.http.post<User>(this.userUrl + '/adduser', user);
  }

 /* public deleteById(id:bigint){
    return this.http.delete<User>(this.userUrl + `/${id}`)
  }*/

  public deleteByCpf(cpf:string){
    return this.http.delete<User>(this.userUrl + `/delete?cpf=` + cpf)
  }

  public edit(id: bigint, user: ɵTypedOrUntyped<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    id: FormControl<string | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }, ɵFormGroupValue<{
    firstName: FormControl<string | null>;
    lastName: FormControl<string | null>;
    cpf: FormControl<string | null>;
    id: FormControl<string | null>;
    cel: FormControl<string | null>;
    email: FormControl<string | null>
  }>, any>){
    return this.http.put<User>(this.userUrl +`/edit/${id}`, user)
  }

  setUserToEditId(id:bigint){
    this.userToEditId$.next(id);
  }

/*  getUserToEditId(){
    return this.selectedUserId$;
  }*/
}
