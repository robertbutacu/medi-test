import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from "./login/components/login/login.component";
import {RegisterComponent} from "./login/components/register/register.component";
const routes: Routes = [
  {
    path: 'test',
    children: [
      {
        path: '',
        loadChildren: './test/test.module#TestModule'
      }
    ]
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
