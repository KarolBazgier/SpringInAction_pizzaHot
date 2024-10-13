import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentPizzasComponent } from './recent-pizzas.component';

describe('RecentPizzasComponent', () => {
  let component: RecentPizzasComponent;
  let fixture: ComponentFixture<RecentPizzasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecentPizzasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecentPizzasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
