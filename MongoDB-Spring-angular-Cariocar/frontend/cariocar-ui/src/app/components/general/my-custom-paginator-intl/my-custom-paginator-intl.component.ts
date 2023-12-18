import { Component, Injectable } from '@angular/core';
import {MatPaginatorIntl} from '@angular/material/paginator';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-my-custom-paginator-intl',
  templateUrl: './my-custom-paginator-intl.component.html',
  styleUrls: ['./my-custom-paginator-intl.component.css']
})
@Injectable()
export class MyCustomPaginatorIntlComponent implements MatPaginatorIntl{
  changes = new Subject<void>();

  getRangeLabel(page: number, pageSize: number, length: number): string {
    if (length === 0) {
      return $localize`Page 1 of 1`;
    }
    const amountPages = Math.ceil(length / pageSize);
    return $localize`Page ${page + 1} of ${amountPages}`;

  }
  firstPageLabel: string =$localize`First page`;
  itemsPerPageLabel: string = $localize`Itens per page:`;
  lastPageLabel: string = $localize`Last page`;
  nextPageLabel: string = $localize`Next page`;
  previousPageLabel = $localize`Previous page`;

}
