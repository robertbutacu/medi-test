import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TestComponent} from './test.component';
import {TestRoutingModule} from './test-routing.module';
import { QuestionListComponent } from './question-list/question-list.component';
import { QuestionComponent } from './question-list/question/question.component';
import { TestGeneratorComponent } from './test-generator/test-generator.component';
import {TestService} from './test.service';

@NgModule({
  imports: [
    CommonModule,
    TestRoutingModule
  ],
  declarations: [
    TestComponent,
    QuestionListComponent,
    QuestionComponent,
    TestGeneratorComponent,
  ],
  providers: [
    TestService
  ]
})
export class TestModule { }
