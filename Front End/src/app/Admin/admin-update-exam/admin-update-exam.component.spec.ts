import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateExamComponent } from './admin-update-exam.component';

describe('AdminUpdateExamComponent', () => {
  let component: AdminUpdateExamComponent;
  let fixture: ComponentFixture<AdminUpdateExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminUpdateExamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminUpdateExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
