import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMngComponent } from './user-mng.component';

describe('UserMngComponent', () => {
  let component: UserMngComponent;
  let fixture: ComponentFixture<UserMngComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserMngComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserMngComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
function beforeEach(arg0: () => void) {
  throw new Error('Function not implemented.');
}

function expect(component: UserMngComponent) {
  throw new Error('Function not implemented.');
}

