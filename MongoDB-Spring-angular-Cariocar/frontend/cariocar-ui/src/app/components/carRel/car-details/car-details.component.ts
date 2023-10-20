import {Component, OnInit} from '@angular/core';
import {Car} from "../../../models/car";
import {CarService} from "../../../services/car/car.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit{
  carPlate!:string | null
  car!:Car
  ngOnInit(): void {
    this.carPlate = this.route.snapshot.paramMap.get('carPlate')
    this.getCar(this.carPlate)
  }

  constructor(private carService:CarService, private route:ActivatedRoute) {
  }

  getCar(plate:string | null){
    this.carService.getCar(String(plate)).subscribe(data => this.car = data)
  }
}
