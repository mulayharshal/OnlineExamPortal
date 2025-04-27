import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminExamresultsComponent } from './admin-examresults.component';

describe('AdminExamresultsComponent', () => {
  let component: AdminExamresultsComponent;
  let fixture: ComponentFixture<AdminExamresultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminExamresultsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminExamresultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
