import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { FormcomponentComponent } from './formcomponent/formcomponent.component';


const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: FormcomponentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }