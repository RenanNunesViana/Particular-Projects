import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Car} from "../../../models/car";
import {CarService} from "../../../services/car/car.service";
import {async, map, Observable} from "rxjs";
import {DialogRef} from "@angular/cdk/dialog";
import {MatDialog} from "@angular/material/dialog";
import {DelDialogCarComponent} from "../del-dialog-car/del-dialog-car.component";

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit{
  displayedColumns:string[] = ['plate', 'model', 'age','ownerCpf', 'options'];
  ds:MatTableDataSource<Car>;
  ds$:Observable<Car[]>
  placeHoldMsg = "CPF do dono ou placa do carro"


  ngOnInit(): void {
    //this.allCars();
    this.ds.filterPredicate = function (data,filter):boolean{
      return String(data.plate).toLowerCase().includes(filter)
        || data.ownerCpf.toLowerCase().includes(filter);
    }
  }

  constructor(private carService:CarService, public dialog:MatDialog) {
    this.ds = new MatTableDataSource<Car>()
    /*this.ds$ = this.carService.findAll().pipe(map(data => {this.ds.data = data
      return this.ds
    }))*/

    this.ds$ = this.carService.findAll()

    this.ds$.subscribe(data => this.ds.data = data)
  }

  onSearchTextEntered(searchValue:string){
    searchValue = searchValue.trim();
    searchValue = searchValue.toLowerCase();

    this.ds.filter = searchValue;

  }

  setSelectedCarPlate(plate:string){
    this.carService.setSelectedCarPlate(plate);
  }

  openDialog(plate:bigint){
    this.dialog.open(DelDialogCarComponent, {
      width:'390px',
      data:{
        plate:plate
      }
    });
  }
}
