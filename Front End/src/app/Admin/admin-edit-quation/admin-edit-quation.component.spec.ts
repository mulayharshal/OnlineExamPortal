import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEditQuationComponent } from './admin-edit-quation.component';

describe('AdminEditQuationComponent', () => {
  let component: AdminEditQuationComponent;
  let fixture: ComponentFixture<AdminEditQuationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminEditQuationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminEditQuationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
