import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppComponent } from './app.component';
import { RecentPizzasComponent } from './recent-pizzas/recent-pizzas.component';

@NgModule({
  declarations: [
    AppComponent,
    RecentPizzasComponent
  ],
  imports: [
    BrowserModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
