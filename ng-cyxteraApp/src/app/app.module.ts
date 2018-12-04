import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';
import { AppRoutingModule } from './app-routing.module';
import {ClientService} from './client/client.service';
import {HttpClientModule} from '@angular/common/http';
import {AddClientComponent} from './client/add-client.component';
import { GrdFilterPipe } from './grd-filter.pipe';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    AddClientComponent,
    GrdFilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
