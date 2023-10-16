import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {OrderService} from "../../../services/order/order.service";
import {PaymentStats} from "../../../models/payment-stats";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent {
  orderForm = new FormGroup({
    id:new FormControl(''),
    description:new FormControl(''),
    note:new FormControl(''),
    customerCpf:new FormControl(''),
    carPlate:new FormControl(''),
    quote:new FormControl(''),
    paymentMade:new FormControl(''),
    paymentStats:new FormControl('')
  })

  paymentStatsOption = [PaymentStats.OPEN, PaymentStats.PARTIAL, PaymentStats.CLOSE]
  selected = PaymentStats.OPEN
  constructor(private orderService:OrderService) {
  }

  onSubmit(){
    this.orderForm.value.paymentStats = this.selected
    this.orderService.save(this.orderForm.value).subscribe(result => {})
    this.orderForm.reset();
  }
}
