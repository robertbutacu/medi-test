import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/services/http.service';
import {Question} from '../models/question.model';
import {HttpClient} from "@angular/common/http";
import {PathprefixService} from "../../shared/services/pathprefix.service";
import { URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class TestService {
  public questionList: Array<Question> = [];
  public isGenerated = false;
  private search: URLSearchParams;
  private headers = new Headers({'Content-Type': 'application/json'});

  private url: string = this.pathprefixService.prefix + '/v1/test/generate';
  private urlScore: string = this.pathprefixService.prefix + '/v1/test/score';

  constructor(private http: HttpClient,
              private pathprefixService: PathprefixService) {
  }

  public getTestJson() {
    return this.http.get('./assets/data/questiondata.json')
      .map((response: Response) => <any>response);
  }

  public generateTest(searchObject) {
    this.setSearch(searchObject);
    return this.http.get(this.url + '?' + this.search )
      .map((response: Response) => <any>response);
  }

  public submitTest(test, userId) {
    return this.http.post(this.urlScore + '?userId=' + userId, test )
      .map((response: Response) => <any>response);
  }

  private setSearch(searchObject: any): void {
    console.log(searchObject);
    this.search = new URLSearchParams();
    for (let key in searchObject) {
      if (searchObject.hasOwnProperty(key)) {
        let val = searchObject[key];
        this.search.set(key, val);
      }
    }
  }
}
