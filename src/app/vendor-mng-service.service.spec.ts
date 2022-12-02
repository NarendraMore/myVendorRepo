import { TestBed } from '@angular/core/testing';

import { VendorMngServiceService } from './vendor-mng-service.service';

describe('VendorMngServiceService', () => {
  let service: VendorMngServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VendorMngServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
