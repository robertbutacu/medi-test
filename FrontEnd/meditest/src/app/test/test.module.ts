import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestionListComponent } from './components/question-list/question-list.component';
import { QuestionComponent } from './components/question-list/question/question.component';
import { TestGeneratorComponent } from './components/test-generator/test-generator.component';
import {TestService} from './services/test.service';
import { FormsModule } from '@angular/forms';
import {CountdownComponent} from "./components/countdown/countdown.component";
import {PathprefixService} from "../shared/services/pathprefix.service";
import {SharedModule} from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule
  ],
  declarations: [
    QuestionListComponent,
    QuestionComponent,
    TestGeneratorComponent,
    CountdownComponent
  ],
  providers: [
    TestService,
    PathprefixService
  ]
})
export class TestModule { }
