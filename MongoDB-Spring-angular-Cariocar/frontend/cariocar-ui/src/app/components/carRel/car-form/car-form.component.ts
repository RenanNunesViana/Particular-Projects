import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CarService} from "../../../services/car/car.service";

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent {
  carForm= new FormGroup({
    plate:new FormControl('', Validators.required),
    model: new FormControl('',Validators.required),
    age: new FormControl('', Validators.required),
    ownerCpf: new FormControl('', Validators.required)
  })


  constructor(private router: Router,
              private carService: CarService) {  }

  onSubmit() {
    this.carService.save(this.carForm.value).subscribe(result=>{})
    this.carForm.reset();

  }
}
