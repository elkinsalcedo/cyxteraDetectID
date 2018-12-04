import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Client } from '../models/client.model';
import { ClientService } from './client.service';


@Component({
  templateUrl: './add-client.component.html'
})
export class AddClientComponent {

  client: Client = new Client();

  constructor(private router: Router, private clientService: ClientService) {

  }

  createClient(): void {
    const startDateFormmated = new DatePipe('en-US').transform(this.client.startDate, 'dd/MM/yyyy');
    const endDateFormmated = new DatePipe('en-US').transform(this.client.endDate, 'dd/MM/yyyy');
    this.client.startDate = startDateFormmated;
    this.client.endDate = endDateFormmated;
    this.clientService.createClient(this.client)
    .subscribe( data => {
        alert('Client created successfully.');
    });
  }
}
