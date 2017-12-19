import {Component, Input, OnInit} from '@angular/core';
import {TestService} from '../../test.service';

@Component({
  selector: 'app-test-generator',
  templateUrl: './test-generator.component.html',
  styleUrls: ['./test-generator.component.scss']
})
export class TestGeneratorComponent implements OnInit {
  public opValue: string;

  fields = [
    {value: 'field-0', viewValue: 'A'},
    {value: 'field-1', viewValue: 'B'},
    {value: 'field-2', viewValue: 'C'}
  ];

  options = [
    {value: 'level-time-0', viewValue: 'Difficulty level and time'},
    {value: 'level-questions-1', viewValue: 'Difficulty level and number of questions'},
    {value: 'questions-time-2', viewValue: 'Number of questions and time'}
  ];

  levels = [
    {value: 'easy-0', viewValue: 'Easy'},
    {value: 'normal-1', viewValue: 'Normal'},
    {value: 'hard-2', viewValue: 'Hard'},
    {value: 'very-hard-3', viewValue: 'Very hard'}
  ];

  times = [
    {value: '5min-0', viewValue: '5 Minutes'},
    {value: '10min-1', viewValue: '10 Minutes'},
    {value: '15min-2', viewValue: '15 Minutes'},
    {value: '20min-3', viewValue: '20 Minutes'},
    {value: '25min-4', viewValue: '25 Minutes'},
    {value: '30min-5', viewValue: '30 Minutes'},
    {value: '35min-6', viewValue: '35 Minutes'},
    {value: '40min-7', viewValue: '40 Minutes'},
    {value: '45min-8', viewValue: '45 Minutes'},
    {value: '50min-9', viewValue: '50 Minutes'},
    {value: '55min-10', viewValue: '55 Minutes'},
    {value: '60min-11', viewValue: '60 Minutes'}
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

  constructor(public testService: TestService) {
  }

  ngOnInit() {
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }

  setValue(value: string): void {
    console.log(value);
    this.opValue = value;
  }

}
