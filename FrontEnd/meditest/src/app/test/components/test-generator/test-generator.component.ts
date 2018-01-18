import {Component, Input, OnInit} from '@angular/core';
import {TestService} from '../../services/test.service';
import {Test} from "../../models/Test";
import {Router} from "@angular/router";

@Component({
  selector: 'app-test-generator',
  templateUrl: './test-generator.component.html',
  styleUrls: ['./test-generator.component.scss']
})
export class TestGeneratorComponent implements OnInit {
  public opValue: string;
  public title: string = 'MediTest';
  test: Test = new Test();

  fields = [
    {value: 'programming', viewValue: 'programming'},
    {value: 'culture', viewValue: 'culture'},
    {value: 'science', viewValue: 'science'}
  ];

  options = [
    {value: 'level-time-0', viewValue: 'Difficulty level and time'},
    {value: 'level-questions-1', viewValue: 'Difficulty level and number of questions'},
    {value: 'questions-time-2', viewValue: 'Number of questions and time'}
  ];

  levels = [
    {value: 'Easy', viewValue: 'Easy'},
    {value: 'Normal', viewValue: 'Normal'},
    {value: 'Hard', viewValue: 'Hard'}
  ];

  times = [
    {value: '5', viewValue: '5 Minutes'},
    {value: '10', viewValue: '10 Minutes'},
    {value: '15', viewValue: '15 Minutes'},
    {value: '20', viewValue: '20 Minutes'},
    {value: '25', viewValue: '25 Minutes'},
    {value: '30', viewValue: '30 Minutes'},
    {value: '35', viewValue: '35 Minutes'},
    {value: '40', viewValue: '40 Minutes'},
    {value: '45', viewValue: '45 Minutes'},
    {value: '50', viewValue: '50 Minutes'},
    {value: '55', viewValue: '55 Minutes'},
    {value: '60', viewValue: '60 Minutes'}
  ];

  questions = [
    {value: '6', viewValue: '6 questions'},
    {value: '7', viewValue: '7 questions'},
    {value: '8', viewValue: '8 questions'},
    {value: '9', viewValue: '9 questions'},
    {value: '10', viewValue: '10 questions'},
    {value: '11', viewValue: '11 questions'},
    {value: '12', viewValue: '12 questions'},
    {value: '13', viewValue: '13 questions'},
    {value: '14', viewValue: '14 questions'},
    {value: '15', viewValue: '15 questions'},
    {value: '16', viewValue: '16 questions'},
    {value: '17', viewValue: '17 questions'},
    {value: '18', viewValue: '18 questions'},
    {value: '19', viewValue: '19 questions'},
    {value: '20', viewValue: '20 questions'},
    {value: '21', viewValue: '21 questions'},
    {value: '22', viewValue: '22 questions'},
    {value: '23', viewValue: '23 questions'},
    {value: '24', viewValue: '24 questions'}
  ];

  constructor(public testService: TestService,
              private router: Router) {
  }

  ngOnInit() {
  }

  private generateTest(): void {
    localStorage.setItem('domain', this.test.domain);
    localStorage.setItem('difficulty', this.test.difficulty);
    localStorage.setItem('time', this.test.time);
    localStorage.setItem('questions', this.test.numberOfQuestions.toString());
    if (this.opValue === 'level-time-0') {
      localStorage.setItem('type', '0');
    } else if (this.opValue === 'level-questions-1') {
      localStorage.setItem('type', '1');
    } else {
      localStorage.setItem('type', '2');
    }
    this.router.navigateByUrl('newtest');
  }

  setValue(value: string): void {
    console.log(value);
    this.opValue = value;
  }

}
