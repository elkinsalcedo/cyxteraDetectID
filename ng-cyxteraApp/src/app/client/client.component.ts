import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../models/client.model';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styles: []
})
export class ClientComponent implements OnInit {

  clients: Client[];
  constructor(private router: Router, private clientService: ClientService) { }

  ngOnInit() {
    this.clientService.getClients()
      .subscribe( data => {
        this.clients = data;
        console.log('data', data, 'this.clients', this.clients[0]);
      });
  }

}
