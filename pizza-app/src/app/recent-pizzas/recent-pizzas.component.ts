import { Component, Injectable, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http'


@Component({
  selector: 'app-recent-pizzas',
  templateUrl: './recent-pizzas.component.html',
  styleUrls: ['./recent-pizzas.component.css']
})

@Injectable()
export class RecentPizzasComponent implements OnInit {
  recentPizzas: any;

  constructor(private httpClient : HttpClient){ }

  ngOnInit(){
      this.httpClient.get('http://localhost:8080/rest/design/recent')
        .subscribe(data => this.recentPizzas = data);
    }
}
