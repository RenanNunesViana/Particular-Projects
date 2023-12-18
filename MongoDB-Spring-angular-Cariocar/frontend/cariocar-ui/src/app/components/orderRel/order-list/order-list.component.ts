import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Order} from "../../../models/order";
import {OrderService} from "../../../services/order/order.service";
import {map, Observable} from "rxjs";
import {DatePipe} from "@angular/common";
import {MatDialog} from "@angular/material/dialog";
import {DelDialogOrderComponent} from "../del-dialog-order/del-dialog-order.component";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit{
  displayedColumns:string[] = ['description','customerCpf','checkin','quote','paymentStats', 'options']
  ds:MatTableDataSource<Order>;
  ds$:Observable<Order[]>
  fromDate!: Date
  toDate!:Date
  pipe!:DatePipe;

  constructor(private orderService:OrderService, public dialog:MatDialog) {
    this.ds = new MatTableDataSource<Order>()
    this.ds$ = this.orderService.findAll()
    this.ds$.subscribe(data => this.ds.data = data);

  }
  ngOnInit(): void {
    this.pipe = new DatePipe("pt-BR")
    this.ds.filterPredicate = (data, filter)=>{
      if(this.fromDate && this.toDate){
        let orderCheckin:Date = new Date(data.checkin);
        console.log("is bigger: " + (orderCheckin >= this.fromDate))
        console.log("is lesser: " + (orderCheckin <= this.toDate))
        console.log("data checkin: " + data.checkin)
        console.log("from date: " + this.fromDate)
        console.log("to date: " + this.toDate)
        return orderCheckin >= this.fromDate && orderCheckin <= this.toDate;
      }
      return true
    }
  }

  onSearchDateBetween(dates: Date[]){
    this.fromDate = dates[0]
    this.toDate = dates[1]
    this.ds.filter = ''+ Math.random();

  }

  openDialog(id:number){
    this.dialog.open(DelDialogOrderComponent, {
      width:'390px',
      data:{
        orderId: id
      }
    })
  }
  setOrderToEditId(orderId:string){
    this.orderService.setOrderToEditId(BigInt(orderId));
  }
}
