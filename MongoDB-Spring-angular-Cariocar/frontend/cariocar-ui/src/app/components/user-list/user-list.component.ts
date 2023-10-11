import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {MatTableDataSource} from '@angular/material/table';
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{
  displayedColumns: string[] = ['id', 'cpf', 'firstName', 'lastName', 'cel','email'];
  ds:MatTableDataSource<User>;
  searchString = ''
  ds$:Observable<MatTableDataSource<User>>;
  ngOnInit(): void {
    this.ds.filterPredicate = function (data,filter):boolean{
      return data.firstName.toLowerCase().includes(filter)
        || data.lastName.toLowerCase().includes(filter)
        || data.cpf.toLowerCase().includes(filter);
    }
  }
  constructor(private userService: UserService) {
    this.ds = new MatTableDataSource;
    this.ds$ = this.userService.findAll().pipe(map(data => {this.ds.data = data
      return this.ds;
    }));
  }

  onSearchTextEntered(searchValue:string){
    searchValue = searchValue.trim();
    searchValue = searchValue.toLowerCase();

    this.ds.filter = searchValue;
    this.searchString = searchValue;

    console.log(this.searchString)
  }
}
