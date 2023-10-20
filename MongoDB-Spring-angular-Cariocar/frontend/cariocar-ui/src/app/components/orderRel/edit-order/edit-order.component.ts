import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../../services/order/order.service";
import {FormControl, FormGroup} from "@angular/forms";
import {PaymentStats} from "../../../models/payment-stats";

@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.css']
})
export class EditOrderComponent implements OnInit{
  orderToEditId!:number;

  constructor(private orderService:OrderService) {
  }
  ngOnInit(): void {
    this.orderService.selectedOrderToEditId$.subscribe(id => this.orderToEditId = id)
  }
  paymentStatsOption = [PaymentStats.OPEN, PaymentStats.PARTIAL, PaymentStats.CLOSE]

  orderForm = new FormGroup({
    id:new FormControl(''),
    description:new FormControl(''),
    note:new FormControl(''),
    customerCpf:new FormControl(''),
    carPlate:new FormControl(''),
    quote:new FormControl(''),
    paymentMade:new FormControl(''),
    paymentStats:new FormControl(''),
    checkin:new FormControl(''),
    checkout:new FormControl(''),
    outstanding:new FormControl('')
  })

  onSubmit(id:string){
    this.orderForm.value.id = id
    this.orderService.edit(Number(id), this.orderForm.value).subscribe()
    this.orderForm.reset()
    window.alert('Ordem editada com sucesso')
  }

  protected readonly String = String;
}

