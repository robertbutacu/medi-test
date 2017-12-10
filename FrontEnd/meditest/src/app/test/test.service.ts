import {Injectable} from '@angular/core';
import {HttpService} from '../shared/services/http.service';
import {Question} from './question-list/question/question.model';

@Injectable()
export class TestService {
  public questionList: Array<Question> = [];
  public isGenerated = false;

  constructor(private http: HttpService) {
  }
}
