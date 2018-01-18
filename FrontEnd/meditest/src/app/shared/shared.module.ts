import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HttpService} from './services/http.service';
import {HttpClientModule} from '@angular/common/http';
import {AuthenticationService} from "./services/authentication.service";
import {AuthGuard} from "./guards/auth.guard";
import {LogoComponent} from "./components/logo/logo.component";
import {NavbarComponent} from "./components/navbar/navbar.component";
import {SuiModule} from 'ng2-semantic-ui';
import {HeaderComponent} from "./components/header/header.component";


@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    SuiModule
  ],
  declarations: [
    LogoComponent,
    NavbarComponent,
    HeaderComponent
  ],
  providers: [
    HttpService,
    AuthenticationService,
    AuthGuard,
  ],
  exports: [
    LogoComponent,
    NavbarComponent,
    SuiModule,
    HeaderComponent
  ]
})
export class SharedModule { }
