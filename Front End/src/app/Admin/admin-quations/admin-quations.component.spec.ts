import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminQuationsComponent } from './admin-quations.component';

describe('AdminQuationsComponent', () => {
  let component: AdminQuationsComponent;
  let fixture: ComponentFixture<AdminQuationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminQuationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminQuationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
