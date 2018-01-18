import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import 'rxjs/add/operator/map';
import {PathprefixService} from "../../shared/services/pathprefix.service";

@Injectable()
export class StatsService {

  private url: string = this.pathprefixService.prefix + '/v1/statistics/';

  constructor(private http: HttpClient,
              private pathprefixService: PathprefixService) {
  }
  public getStats(id) {
    return this.http.get(this.url + '?userId=' + id)
      .map((response: Response) => <any>response);
  }
}
