import {Component, OnInit} from '@angular/core';
import {TestService} from "../../services/test.service";
import {Test} from "../../models/Test";
import {Router} from "@angular/router";
import {isNullOrUndefined} from "util";

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
  public userId;
  public testSubmit: Test = new Test();

  constructor(public testService: TestService,
              public router: Router) {
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
    this.userId = localStorage.getItem('id');
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
    // this.questions[this.questionCount - 1].value.answered = true;
    this.questionCount = this.questionCount + value;
    if (this.questionCount == 0)
      this.questionCount = 1;
    if (this.questionCount > this.maxLength)
      this.questionCount = this.maxLength;
  }

  jumpTo(index) {
    // this.questions[this.questionCount - 1].value.answered = true;
    this.questionCount = index + 1;
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }

  private getCurrentDate(): void {
    this.currentDate = new Date(this.currentDate.getTime() + 30 * 60000);
  }

  submitTest() {
    this.testSubmit.questions = [];
    this.testSubmit.domain = this.test.domain;
    this.testSubmit.difficulty = this.test.difficulty;
    this.testSubmit.duration = this.test.duration;
    for (let i = 0; i < this.test.questions.length; i++) {
      this.testSubmit.questions.push({'key': this.test.questions[i].key, 'answers': []});
      if (!isNullOrUndefined(this.test.questions[i].value.answers)) {
        for (let j = 0; j < this.test.questions[i].value.answers.length; j++) {
          this.testSubmit.questions[i].answers.push(this.test.questions[i].value.answers[j]);
        }
      } else {
        for (let j = 0; j < this.test.questions[i].value.matches.length; j++) {
          this.testSubmit.questions[i].answers.push(this.test.questions[i].value.matches[j].key);
        }
      }
    }
    // console.log(this.testSubmit);
    this.testService.submitTest(this.testSubmit, this.userId).subscribe(
      (succes) => {
        this.router.navigateByUrl('statistics');
      }
    )
  }

}
