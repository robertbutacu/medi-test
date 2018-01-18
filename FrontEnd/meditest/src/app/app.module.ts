import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {TestModule} from './test/test.module';
import { HeaderComponent } from './shared/components/header/header.component';
import {SharedModule} from './shared/shared.module';
import {HttpModule} from "@angular/http";
import {LoginModule} from "./login/login.module";
import {StatsModule} from "./stats/stats.module";


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TestModule,
    SharedModule,
    LoginModule,
    StatsModule,
    HttpModule
  ],
  providers: [],
  exports:[SharedModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
