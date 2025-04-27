import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCreateExamComponent } from './admin-create-exam.component';

describe('AdminCreateExamComponent', () => {
  let component: AdminCreateExamComponent;
  let fixture: ComponentFixture<AdminCreateExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminCreateExamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCreateExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
