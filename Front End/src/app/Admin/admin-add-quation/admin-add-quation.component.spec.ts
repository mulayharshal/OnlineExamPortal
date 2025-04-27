import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddQuationComponent } from './admin-add-quation.component';

describe('AdminAddQuationComponent', () => {
  let component: AdminAddQuationComponent;
  let fixture: ComponentFixture<AdminAddQuationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAddQuationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddQuationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
