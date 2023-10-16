import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {Order} from "../../../models/order";
import {OrderService} from "../../../services/order/order.service";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit{
  displayedColumns:string[] = ['description','customerCpf','checkin','quote','paymentStats', 'options']
  dataSource=new MatTableDataSource<Order>()
  searchString:string =''

  constructor(private orderService:OrderService) {
  }
  ngOnInit(): void {
    this.allOrders()
  }

  allOrders(){
    this.orderService.findAll().subscribe(data => this.dataSource.data = data);
  }
}
