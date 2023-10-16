import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrderListComponent} from "../../components/orderRel/order-list/order-list.component";
import {OrderFormComponent} from "../../components/orderRel/order-form/order-form.component";

const routes: Routes = [
  {path:'list', component:OrderListComponent},
  {path:'register', component:OrderFormComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
