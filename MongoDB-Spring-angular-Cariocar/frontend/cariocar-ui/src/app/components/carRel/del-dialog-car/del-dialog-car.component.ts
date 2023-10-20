import {Component, Inject} from '@angular/core';
import {UserService} from "../../../services/user/user.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {InterfaceToDelete} from "../../../source/interface-to-delete";
import {CarService} from "../../../services/car/car.service";

@Component({
  selector: 'app-del-dialog-car',
  templateUrl: './del-dialog-car.component.html',
  styleUrls: ['./del-dialog-car.component.css']
})
export class DelDialogCarComponent {

  plate!:bigint;

  constructor(public dialogRef: MatDialogRef<DelDialogCarComponent>, private carService:CarService, @Inject(MAT_DIALOG_DATA) public data:InterfaceToDelete) {
    this.plate = data.plate;
  }

  onSubmit(){
    this.carService.delete(this.plate).subscribe()
  }

  onNoSubmit(){
    this.dialogRef.close()
  }
}
