import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./components/user-list/user-list.component";
import {UserFormComponent} from "./components/user-form/user-form.component";
import {CarListComponent} from "./components/car-list/car-list.component";
import {CarFormComponent} from "./components/car-form/car-form.component";
import {OrderListComponent} from "./components/order-list/order-list.component";
import {OrderFormComponent} from "./components/order-form/order-form.component";

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
