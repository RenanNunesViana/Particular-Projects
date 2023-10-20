import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DelDialogCarComponent } from './del-dialog-car.component';

describe('DelDialogCarComponent', () => {
  let component: DelDialogCarComponent;
  let fixture: ComponentFixture<DelDialogCarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DelDialogCarComponent]
    });
    fixture = TestBed.createComponent(DelDialogCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
