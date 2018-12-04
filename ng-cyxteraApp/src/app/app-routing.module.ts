import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientComponent } from './client/client.component';
import {AddClientComponent} from './client/add-client.component';

const routes: Routes = [
  { path: 'clients', component: ClientComponent },
  { path: 'add', component: AddClientComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
