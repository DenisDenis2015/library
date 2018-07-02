import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchStringComponent } from './search-string.component';

describe('SearchStringComponent', () => {
  let component: SearchStringComponent;
  let fixture: ComponentFixture<SearchStringComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchStringComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchStringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
