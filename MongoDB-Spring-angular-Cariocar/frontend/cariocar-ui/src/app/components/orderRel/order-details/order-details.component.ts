import {Component, OnInit} from '@angular/core';
import {Order} from "../../../models/order";
import {OrderService} from "../../../services/order/order.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit{
  orderId!:string | null;
  order!:Order
  constructor(private orderService:OrderService, private route:ActivatedRoute) {

  }

  ngOnInit(): void {
    this.orderId = this.route.snapshot.paramMap.get('orderId')
    this.getOrder(this.orderId)
  }

  private getOrder(orderId: string | null){
    this.orderService.findById(Number(orderId)).subscribe(data => this.order = data)
  }

}
