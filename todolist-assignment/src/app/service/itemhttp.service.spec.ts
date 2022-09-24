import { TestBed } from '@angular/core/testing';

import { ItemhttpService } from './itemhttp.service';

describe('ItemhttpService', () => {
  let service: ItemhttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItemhttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
