import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserFormComponent} from "../../components/userRel/user-form/user-form.component";
import {UserListComponent} from "../../components/userRel/user-list/user-list.component";
import {EditUserComponent} from "../../components/userRel/edit-user/edit-user.component";
import {UserDetailsComponent} from "../../components/userRel/user-details/user-details.component";

const routes: Routes = [
  {path:'register', component:UserFormComponent},
  {path:'list', component:UserListComponent},
  {path:'edit/:userId', component:EditUserComponent},
  {path:'details/:userId', component:UserDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
