import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindByDateComponent } from './find-by-date.component';

describe('FindByDateComponent', () => {
  let component: FindByDateComponent;
  let fixture: ComponentFixture<FindByDateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FindByDateComponent]
    });
    fixture = TestBed.createComponent(FindByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
