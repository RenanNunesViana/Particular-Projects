import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-find-by-date',
  templateUrl: './find-by-date.component.html',
  styleUrls: ['./find-by-date.component.css']
})
export class FindByDateComponent {



  @Output()
  searchDateBetween:EventEmitter<any> = new EventEmitter<any>()


  filterForm = new FormGroup({
    fromDate: new FormControl(),
    toDate: new FormControl(),
  });

  get fromDate() { return this.filterForm.get('fromDate')?.value; }
  get toDate() { return this.filterForm.get('toDate')?.value; }

  onSearchDateChanged(){
    this.searchDateBetween.emit([this.fromDate, this.toDate])

  }
}
