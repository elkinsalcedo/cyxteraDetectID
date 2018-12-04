import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Client } from '../models/client.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ClientService {

  constructor(private http: HttpClient) {}
    private clientUrl = 'http://localhost:8080/v1';

  public getClients() {
    return this.http.get<Client[]>(this.clientUrl + '/getclients');
  }

  public createClient(client) {
    return this.http.post<Client>(this.clientUrl + '/add', client);
  }
}

