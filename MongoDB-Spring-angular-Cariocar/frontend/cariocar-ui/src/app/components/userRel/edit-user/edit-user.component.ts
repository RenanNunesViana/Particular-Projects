import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user/user.service";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit{
  userToEditId = BigInt(0)
  ngOnInit(): void {
    this.userService.selectedUserId$.subscribe(id =>{
      this.userToEditId = id;
    })
  }

  userForm= new FormGroup({
    id:new FormControl('', Validators.required),
    cpf: new FormControl('',Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    cel: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
  })

  constructor(private userService: UserService) {
  }
  onSubmit(id:bigint) {
    this.userForm.value.id = String(id)
    this.userService.edit(id, this.userForm.value).subscribe(()=>{})
    this.userForm.reset();
    window.alert("Usuário editado com sucesso")
  }
}
