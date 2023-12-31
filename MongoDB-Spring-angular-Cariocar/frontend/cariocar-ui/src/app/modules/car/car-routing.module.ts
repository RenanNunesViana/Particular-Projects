import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarListComponent} from "../../components/carRel/car-list/car-list.component";
import {CarFormComponent} from "../../components/carRel/car-form/car-form.component";
import {EditCarComponent} from "../../components/carRel/edit-car/edit-car.component";
import {CarDetailsComponent} from "../../components/carRel/car-details/car-details.component";

const routes: Routes = [
  {path:'list', component:CarListComponent},
  {path:'register', component:CarFormComponent},
  {path:'edit/:carPlate', component:EditCarComponent},
  {path:'details/:carPlate', component:CarDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingModule { }
