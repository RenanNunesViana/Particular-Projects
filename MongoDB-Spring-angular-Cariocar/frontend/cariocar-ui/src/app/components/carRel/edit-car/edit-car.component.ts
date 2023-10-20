import {Component, OnInit} from '@angular/core';
import {CarService} from "../../../services/car/car.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-edit-car',
  templateUrl: './edit-car.component.html',
  styleUrls: ['./edit-car.component.css']
})
export class EditCarComponent implements OnInit {
  carToEditPlate!: string;

  carForm= new FormGroup({
    plate:new FormControl('', Validators.required),
    model: new FormControl('',Validators.required),
    age: new FormControl('', Validators.required),
    ownerCpf: new FormControl('', Validators.required)
  })


  ngOnInit(): void {
    this.carService.selectedCarPlate$.subscribe(plate=>{
      this.carToEditPlate = plate
    })
  }

  constructor(private carService:CarService) {
  }

  onSubmit(plate:string){
    this.carForm.value.plate = plate
    this.carService.edit(plate, this.carForm.value).subscribe()
    this.carForm.reset();
    window.alert("carro editado com sucesso!")
  }
}
