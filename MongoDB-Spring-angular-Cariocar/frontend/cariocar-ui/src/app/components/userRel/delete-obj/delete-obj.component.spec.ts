import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteObjComponent } from './delete-obj.component';

describe('DeleteObjComponent', () => {
  let component: DeleteObjComponent;
  let fixture: ComponentFixture<DeleteObjComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteObjComponent]
    });
    fixture = TestBed.createComponent(DeleteObjComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
