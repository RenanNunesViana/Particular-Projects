import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule} from "@angular/common/http";
import { UserFormComponent } from './components/userRel/user-form/user-form.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from "@angular/forms";
import { UserListComponent } from './components/userRel/user-list/user-list.component';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import { CarListComponent } from './components/carRel/car-list/car-list.component';
import { OrderListComponent } from './components/orderRel/order-list/order-list.component';
import { CarFormComponent } from './components/carRel/car-form/car-form.component';
import { OrderFormComponent } from './components/orderRel/order-form/order-form.component';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from "@angular/material/icon";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatChipsModule} from "@angular/material/chips";

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSelectModule} from "@angular/material/select";
import {MatMenuModule} from '@angular/material/menu';
import {MatSortModule} from "@angular/material/sort";
import { EditUserComponent } from './components/userRel/edit-user/edit-user.component';
import {EditCarComponent} from "./components/carRel/edit-car/edit-car.component";
import {EditOrderComponent} from "./components/orderRel/edit-order/edit-order.component";
import {DeleteObjComponent} from "./components/userRel/delete-obj/delete-obj.component";
import { FindBarComponent } from './components/general/find-bar/find-bar.component';
import { FindByDateComponent } from './components/general/find-by-date/find-by-date.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import { UserDetailsComponent } from './components/userRel/user-details/user-details.component';
import {MatDialogModule} from "@angular/material/dialog";
import { CarDetailsComponent } from './components/carRel/car-details/car-details.component';
import { OrderDetailsComponent } from './components/orderRel/order-details/order-details.component';
import { DelDialogCarComponent } from './components/carRel/del-dialog-car/del-dialog-car.component';
import { DelDialogOrderComponent } from './components/orderRel/del-dialog-order/del-dialog-order.component';

@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    UserListComponent,
    CarListComponent,
    OrderListComponent,
    CarFormComponent,
    OrderFormComponent,
    EditUserComponent,
    EditCarComponent,
    EditOrderComponent,
    DeleteObjComponent,
    FindBarComponent,
    FindByDateComponent,
    UserDetailsComponent,
    CarDetailsComponent,
    OrderDetailsComponent,
    DelDialogCarComponent,
    DelDialogOrderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatTableModule,
    MatButtonModule,
    FormsModule,
    MatIconModule,
    MatAutocompleteModule,
    MatChipsModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatMenuModule,
    MatSortModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
