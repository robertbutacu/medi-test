import {Component, Input, OnInit} from '@angular/core';
import {TestService} from '../../test.service';

@Component({
  selector: 'app-test-generator',
  templateUrl: './test-generator.component.html',
  styleUrls: ['./test-generator.component.scss']
})
export class TestGeneratorComponent implements OnInit {

  options = [
    {value: 'level-time-0', viewValue: 'Level and time'},
    {value: 'level-questions-1', viewValue: 'Level and number of questions'},
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
    {value: '5-0', viewValue: '5 questions'},
    {value: '10-0', viewValue: '10 questions'},
    {value: '15-0', viewValue: '15 questions'},
    {value: '20-0', viewValue: '20 questions'},
    {value: '25-0', viewValue: '25 questions'},
    {value: '30-0', viewValue: '30 questions'},
    {value: '40-0', viewValue: '40 questions'},
    {value: '50-0', viewValue: '50 questions'},
    {value: '75-0', viewValue: '75 questions'},
    {value: '100-0', viewValue: '100 questions'}
  ];

  constructor(public testService: TestService) {
  }

  ngOnInit() {
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }
}
