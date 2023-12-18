import {Component, OnInit} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";
import {MatTableDataSource} from '@angular/material/table';
import {map, Observable} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {DeleteObjComponent} from "../delete-obj/delete-obj.component";
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{
  displayedColumns: string[] = ['cpf', 'firstName', 'lastName', 'cel', 'options'];
  ds:MatTableDataSource<User>;
  /*ds$:Observable<MatTableDataSource<User>>;*/
  ds$:Observable<User[]>;
  placeHoldMsg = "CPF ou Nome";
  ngOnInit(): void {
    this.ds.filterPredicate = function (data,filter):boolean{
      return data.firstName.toLowerCase().includes(filter)
        || data.lastName.toLowerCase().includes(filter)
        || data.cpf.toLowerCase().includes(filter);
    }
  }
  constructor(private userService: UserService, public dialog:MatDialog) {
    this.ds = new MatTableDataSource;
    /*this.ds$ = this.userService.findAll().pipe(map(data => {this.ds.data = data
      return this.ds;
    }));*/
    /*this.userService.findAll().subscribe(data => this.ds.data = data)*/

    this.ds$ = this.userService.findAll()

    this.ds$.subscribe(data => this.ds.data = data)
  }

  onSearchTextEntered(searchValue:string){
    searchValue = searchValue.trim();
    searchValue = searchValue.toLowerCase();

    this.ds.filter = searchValue;

  }

  setUserToEditId(id:bigint){
    this.userService.setUserToEditId(id);
  }

  openDialog(cpf:string){
    const dialogRef = this.dialog.open(DeleteObjComponent, {
      width:'390px',
      data:{
        cpf:cpf
      }});
  }
}
