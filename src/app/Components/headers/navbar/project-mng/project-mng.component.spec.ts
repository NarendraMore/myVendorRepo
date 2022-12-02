import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectMngComponent } from './project-mng.component';

describe('ProjectMngComponent', () => {
  let component: ProjectMngComponent;
  let fixture: ComponentFixture<ProjectMngComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjectMngComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
