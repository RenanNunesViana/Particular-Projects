import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Car} from "../../models/car";
import {CarService} from "../../services/car/car.service";
import {async} from "rxjs";

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit{
  displayedColumns:string[] = ['plate', 'model', 'age','ownerCpf'];
  dataSource = new MatTableDataSource<Car>();
  //searchString:string = ''

  /*getData() {
  const
  testData = [{
    "plate": "123456789",
    "model":"fordcar",
    "age": 2020,
    "ownerCpf":"123456789"
  },
    {
      "plate":"987654321",
      "model":"ferrari",
      "age":2022,
      "ownerCpf":"123456789"
    }
  ];
    this.dataSource.data = testData;
}*/

  ngOnInit(): void {
    this.allCars();
    this.dataSource.filterPredicate = function (data,filter):boolean{
      return data.plate.toLowerCase().includes(filter)
        || data.ownerCpf.toLowerCase().includes(filter);
    }
    /*this.getData();*/
  }

  constructor(private carService:CarService) {
  }

  allCars(){
    this.carService.findAll().subscribe(data =>{
      this.dataSource.data = data;
      })

  }

/*
  onSearchTextEntered(searchValue:string){
    searchValue = searchValue.trim();
    searchValue = searchValue.toLowerCase();

    this.dataSource.filter = searchValue;
    this.searchString = searchValue;

  }
*/
  protected readonly async = async;
}
