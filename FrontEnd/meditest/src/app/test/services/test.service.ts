import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/services/http.service';
import {Question} from '../models/question.model';
import {HttpClient} from "@angular/common/http";
import {PathprefixService} from "../../shared/services/pathprefix.service";

@Injectable()
export class TestService {
  public questionList: Array<Question> = [];
  public isGenerated = false;

  private url: string = this.pathprefixService.prefix + '/v1/test/generate';

  constructor(
    private http: HttpClient,
    private pathprefixService: PathprefixService
  ) {
  }

  public getTest(numberOfQuestions: number, difficulty: string) {
    // if (!this.isGenerated) {
    //   this.http.get("/questions?numberOfQuestions=" + numberOfQuestions + "&difficulty=" + difficulty)
    //     .subscribe(
    //       (response) => response.map(question => {
    //         return {
    //           question: question.body
    //         }
    //       }).forEach(question => this.questionList.push(question))
    //     )
    //   ;
    // }
    this.isGenerated = true;
    return this.questionList;
  }

  public getTestJson() {
    return this.http.get('./assets/data/questiondata.json')
      .map((response: Response) => <any>response);
  }

  public generateTest(numberOfQuestions: number, difficulty: string) {
   return this.http.get(this.url + "?domain=programming&numberOfQuestions=" + numberOfQuestions + '&difficulty=' + difficulty )
  .map((response: Response) => <any>response);
  }
}
