import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecentPizzasComponent } from './recent-pizzas/recent-pizzas.component';
export const routes: Routes = [

  {
    path: 'recents',
    component: RecentPizzasComponent
  },
 
  {
    path: '**',
    redirectTo: '/',
    pathMatch: 'full'
  },
];