import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./components/userRel/user-list/user-list.component";
import {UserFormComponent} from "./components/userRel/user-form/user-form.component";
import {CarListComponent} from "./components/carRel/car-list/car-list.component";
import {CarFormComponent} from "./components/carRel/car-form/car-form.component";
import {OrderListComponent} from "./components/orderRel/order-list/order-list.component";
import {OrderFormComponent} from "./components/orderRel/order-form/order-form.component";

const routes: Routes = [
  {path:'user', loadChildren:()=>import('./modules/user/user.module')
      .then(mod => mod.UserModule)
  },
  {path:'car', loadChildren:()=>import('./modules/car/car.module')
      .then(mod=>mod.CarModule)
  },
  {path:'order', loadChildren:()=>import('./modules/order/order.module')
      .then(mod=>mod.OrderModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
