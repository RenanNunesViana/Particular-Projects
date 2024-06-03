import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarListComponent} from "../../components/car-list/car-list.component";
import {CarFormComponent} from "../../components/car-form/car-form.component";

const routes: Routes = [
  {path:'list', component:CarListComponent},
  {path:'register', component:CarFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingModule { }
