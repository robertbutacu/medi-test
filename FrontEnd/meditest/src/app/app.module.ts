import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {TestModule} from './test/test.module';
import { HeaderComponent } from './header/header.component';
import {SharedModule} from './shared/shared.module';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TestModule,
    SharedModule
  ],
  providers: [],
  exports:[SharedModule],
  bootstrap: [AppComponent]
})
export class AppModule {
}
