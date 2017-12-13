import {Component, OnInit} from '@angular/core';
import {TestService} from "../../test.service";

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.scss']
})
export class QuestionListComponent implements OnInit {

  public questionCount: number = 1;
  public currentDate: Date = new Date();

  constructor(public testService: TestService) {
  }

  ngOnInit() {
    this.getCurrentDate();
  }

  countQuestion(value) {
    this.questionCount = this.questionCount + value;
    if (this.questionCount == 0)
      this.questionCount = 1;
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }

  private getCurrentDate(): void {
    this.currentDate = new Date(this.currentDate.getTime() + 30*60000);
  }

}
