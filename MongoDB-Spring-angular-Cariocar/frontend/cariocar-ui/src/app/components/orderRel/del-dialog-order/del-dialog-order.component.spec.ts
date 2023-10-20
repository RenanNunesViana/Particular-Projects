import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DelDialogOrderComponent } from './del-dialog-order.component';

describe('DelDialogOrderComponent', () => {
  let component: DelDialogOrderComponent;
  let fixture: ComponentFixture<DelDialogOrderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DelDialogOrderComponent]
    });
    fixture = TestBed.createComponent(DelDialogOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
