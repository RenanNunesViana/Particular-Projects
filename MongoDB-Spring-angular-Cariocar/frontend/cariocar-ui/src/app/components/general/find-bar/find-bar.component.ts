import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-find-bar',
  templateUrl: './find-bar.component.html',
  styleUrls: ['./find-bar.component.css']
})
export class FindBarComponent {
  searchedValue:string = ''

  @Input()
  placeHold = ''
  @Output()
  searchTextChanged:EventEmitter<string> = new EventEmitter<string>()

  onSearchTextChanged(){
    this.searchTextChanged.emit(this.searchedValue)

  }
}
