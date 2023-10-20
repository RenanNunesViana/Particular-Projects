import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {OrderListComponent} from "../../components/orderRel/order-list/order-list.component";
import {OrderFormComponent} from "../../components/orderRel/order-form/order-form.component";
import {EditOrderComponent} from "../../components/orderRel/edit-order/edit-order.component";
import {OrderDetailsComponent} from "../../components/orderRel/order-details/order-details.component";

const routes: Routes = [
  {path:'list', component:OrderListComponent},
  {path:'register', component:OrderFormComponent},
  {path:'edit/:orderId', component:EditOrderComponent},
  {path:'details/:orderId', component: OrderDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
