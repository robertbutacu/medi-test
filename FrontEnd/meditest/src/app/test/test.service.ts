import {Injectable} from '@angular/core';
import {HttpService} from '../shared/services/http.service';
import {Question} from './models/question.model';

@Injectable()
export class TestService {
  public questionList: Array<Question> = [];
  public isGenerated = false;

  constructor(private http: HttpService) {
  }

  public getTest(numberOfQuestions: number, difficulty: string) {
    if (!this.isGenerated) {
      this.http.get("/questions?numberOfQuestions=" + numberOfQuestions + "&difficulty=" + difficulty)
        .subscribe(
          (response) => response.map(question => {
            return {
              question: question.body
            }
          }).forEach(question => this.questionList.push(question))
        )
      ;
    }
    this.isGenerated = true;
    return this.questionList;
  }
}
