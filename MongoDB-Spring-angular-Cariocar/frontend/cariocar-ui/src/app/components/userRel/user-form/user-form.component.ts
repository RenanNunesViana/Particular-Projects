import { Component } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {UserService} from "../../../services/user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  userForm= new FormGroup({
    id:new FormControl('', Validators.required),
    cpf: new FormControl('',Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    cel: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
  })

  constructor(private userService: UserService) { }
  onSubmit() {
    this.userService.save(this.userForm.value).subscribe(()=>{})
    this.userForm.reset();
    window.location.reload();
  }
}
