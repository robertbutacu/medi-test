import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from "./login/components/login/login.component";
import {RegisterComponent} from "./login/components/register/register.component";
import {TestGeneratorComponent} from "./test/components/test-generator/test-generator.component";
import {AuthGuard} from "./shared/guards/auth.guard";
import {QuestionListComponent} from "./test/components/question-list/question-list.component";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'generate', component: TestGeneratorComponent, canActivate: [AuthGuard]},
  {path: 'newtest', component: QuestionListComponent, canActivate: [AuthGuard]},
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
