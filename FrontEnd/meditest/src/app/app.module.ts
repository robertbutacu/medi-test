import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {TestModule} from './test/test.module';
import { HeaderComponent } from './header/header.component';
import {SharedModule} from './shared/shared.module';
import {HttpModule} from "@angular/http";
import {LoginModule} from "./login/login.module";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TestModule,
    SharedModule,
    LoginModule,
    HttpModule
  ],
  providers: [],
  exports:[SharedModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
