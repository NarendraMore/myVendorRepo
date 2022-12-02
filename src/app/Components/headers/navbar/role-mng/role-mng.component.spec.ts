import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleMngComponent } from './role-mng.component';

describe('RoleMngComponent', () => {
  let component: RoleMngComponent;
  let fixture: ComponentFixture<RoleMngComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoleMngComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoleMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
function beforeEach(arg0: () => Promise<void>) {
  throw new Error('Function not implemented.');
}

function expect(component: RoleMngComponent) {
  throw new Error('Function not implemented.');
}

