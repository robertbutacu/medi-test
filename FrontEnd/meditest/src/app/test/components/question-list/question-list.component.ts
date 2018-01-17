import {Component, OnInit} from '@angular/core';
import {TestService} from "../../services/test.service";
import {Test} from "../../models/Test";

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.scss']
})
export class QuestionListComponent implements OnInit {

  public questionCount: number = 1;
  public maxLength: number = 0;
  public currentDate: Date = new Date();
  public test: any;
  public generatedTest: Test = new Test();
  public questions: Array<any> = [];

  constructor(public testService: TestService) {
  }

  ngOnInit() {
    if (localStorage.getItem('type') === '0') {
      this.generatedTest.difficulty = localStorage.getItem('difficulty');
      this.generatedTest.time = localStorage.getItem('time');
    } else if (localStorage.getItem('type') === '1') {
      this.generatedTest.difficulty = localStorage.getItem('difficulty');
      this.generatedTest.numberOfQuestions = Number(localStorage.getItem('questions'));
    } else {
      this.generatedTest.numberOfQuestions = Number(localStorage.getItem('questions'));
      this.generatedTest.time = localStorage.getItem('time');
    }
    this.generatedTest.domain = localStorage.getItem('domain');
    this.startTest();
    this.getCurrentDate();
  }

  // getTest() {
  //   this.testService.getTestJson().subscribe(
  //     (response) => {
  //       this.test = response;
  //       this.questions = response.questions;
  //       this.maxLength = response.questions.length;
  //     }
  //   )
  // }

  startTest() {
    this.testService.generateTest(this.generatedTest).subscribe(
      (response) => {
        this.test = response;
        this.questions = response.questions;
        this.maxLength = response.questions.length;
      }
    )
  }

  countQuestion(value) {
    this.questions[this.questionCount - 1].value.answered = true;
    this.questionCount = this.questionCount + value;
    if (this.questionCount == 0)
      this.questionCount = 1;
    if (this.questionCount > this.maxLength)
      this.questionCount = this.maxLength;
  }

  jumpTo(index) {
    this.questions[this.questionCount - 1].value.answered = true;
    this.questionCount = index + 1;
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }

  private getCurrentDate(): void {
    this.currentDate = new Date(this.currentDate.getTime() + 30 * 60000);
  }

}
