import { TestBed } from '@angular/core/testing';

import { TemplatebuilderService } from './templatebuilder.service';

describe('TemplatebuilderService', () => {
  let service: TemplatebuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TemplatebuilderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
