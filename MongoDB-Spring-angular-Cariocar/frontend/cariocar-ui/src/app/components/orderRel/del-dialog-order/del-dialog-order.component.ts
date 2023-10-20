import {Component, Inject} from '@angular/core';
import {OrderService} from "../../../services/order/order.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {InterfaceToDelete} from "../../../source/interface-to-delete";

@Component({
  selector: 'app-del-dialog-order',
  templateUrl: './del-dialog-order.component.html',
  styleUrls: ['./del-dialog-order.component.css']
})
export class DelDialogOrderComponent {

  orderId!:number

  constructor(public dialogRef:MatDialogRef<DelDialogOrderComponent>, private orderService:OrderService, @Inject(MAT_DIALOG_DATA) public data:InterfaceToDelete) {
    this.orderId = Number(data.orderId);
  }

  onSubmit(){
    this.orderService.delete(this.orderId).subscribe();
  }

  onNoSubmit(){
    this.dialogRef.close();
  }
}
