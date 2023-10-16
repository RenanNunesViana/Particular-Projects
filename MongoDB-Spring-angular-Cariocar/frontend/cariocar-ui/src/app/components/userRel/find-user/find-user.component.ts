import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from "../../../models/user";
import {UserService} from "../../../services/user/user.service";

@Component({
  selector: 'app-find-user',
  templateUrl: './find-user.component.html',
  styleUrls: ['./find-user.component.css']
})
export class FindUserComponent {

  searchedValue:string = ''

  @Output()
  searchTextChanged:EventEmitter<string> = new EventEmitter<string>()

  onSearchTextChanged(){
    this.searchTextChanged.emit(this.searchedValue)

  }
}
