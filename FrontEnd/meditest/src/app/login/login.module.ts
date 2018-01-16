import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {SharedModule} from "../shared/shared.module";
import {LoginService} from "./services/login/login.service";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {CustomFormsModule} from "ng2-validation";
import {PathprefixService} from "../shared/services/pathprefix.service";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    RouterModule,
    FormsModule,
    CustomFormsModule
  ],
  providers: [
    LoginService,
    PathprefixService
  ],
  declarations: [LoginComponent, RegisterComponent]
})
export class LoginModule { }
