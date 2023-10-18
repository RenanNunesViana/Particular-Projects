import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Car} from "../../../models/car";
import {CarService} from "../../../services/car/car.service";
import {async, map, Observable} from "rxjs";

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit{
  displayedColumns:string[] = ['plate', 'model', 'age','ownerCpf', 'options'];
  ds:MatTableDataSource<Car>;
  ds$:Observable<MatTableDataSource<Car>>
  placeHoldMsg = "CPF do dono ou placa do carro"


  ngOnInit(): void {
    //this.allCars();
    this.ds.filterPredicate = function (data,filter):boolean{
      return String(data.plate).toLowerCase().includes(filter)
        || data.ownerCpf.toLowerCase().includes(filter);
    }
  }

  constructor(private carService:CarService) {
    this.ds = new MatTableDataSource<Car>()
    this.ds$ = this.carService.findAll().pipe(map(data => {this.ds.data = data
      return this.ds
    }))
  }

  /*allCars(){
    this.carService.findAll().subscribe(data =>{
      this.ds.data = data;
      })

  }*/

  onSearchTextEntered(searchValue:string){
    searchValue = searchValue.trim();
    searchValue = searchValue.toLowerCase();

    this.ds.filter = searchValue;

  }

}
