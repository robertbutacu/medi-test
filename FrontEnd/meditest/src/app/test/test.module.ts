import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TestComponent} from './components/test/test.component';
import {TestRoutingModule} from './test-routing.module';
import { QuestionListComponent } from './components/question-list/question-list.component';
import { QuestionComponent } from './components/question-list/question/question.component';
import { TestGeneratorComponent } from './components/test-generator/test-generator.component';
import {TestService} from './services/test.service';
import { FormsModule } from '@angular/forms';
import {CountdownComponent} from "./components/countdown/countdown.component";

@NgModule({
  imports: [
    CommonModule,
    TestRoutingModule,
    FormsModule
  ],
  declarations: [
    TestComponent,
    QuestionListComponent,
    QuestionComponent,
    TestGeneratorComponent,
    CountdownComponent
  ],
  providers: [
    TestService
  ]
})
export class TestModule { }
