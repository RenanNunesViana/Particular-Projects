import {Component, Inject, Input, OnInit} from '@angular/core';
import {UserService} from "../../../services/user/user.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {InterfaceToDelete} from "../../../source/interface-to-delete";

@Component({
  selector: 'app-delete-obj',
  templateUrl: './delete-obj.component.html',
  styleUrls: ['./delete-obj.component.css']
})


export class DeleteObjComponent implements OnInit{

  @Input()
  currentlyUserCpf:string
  confirmedUserCpf!:string


  constructor(public dialogRef: MatDialogRef<DeleteObjComponent>, private userService:UserService, @Inject(MAT_DIALOG_DATA) public data:InterfaceToDelete) {
    this.currentlyUserCpf = data.cpf
    console.log(this.currentlyUserCpf)
  }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.currentlyUserCpf === this.confirmedUserCpf){
      this.userService.deleteByCpf(this.currentlyUserCpf).subscribe()
        //location.reload()
    }
    else{
      window.alert("cpf diferente do usuário selecionado")
    }
  }

  onNoConfirmed(){
    this.dialogRef.close();
  }

}
