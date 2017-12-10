import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class HttpService {

  private baseUrl= 'url';

  constructor(private http: HttpClient) { }

  get<T>(url: string): Observable<any> {
    const completeUrl: string = this.baseUrl + url;

    return this.http.get(completeUrl, {headers: this.buildHeaders()});
  }

  post<T>(url: string, data: any): Observable<any> {
    const body: string = JSON.stringify(data);
    const completeUrl: string = this.baseUrl + url;

    return this.http.post(completeUrl, body, {headers: this.buildHeaders()});
  }

  private buildHeaders(): HttpHeaders {
    const headers: HttpHeaders = new HttpHeaders();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    headers.append('Cache-Control', 'no-cache');
    headers.append('Pragma', 'no-cache');
    headers.append('Expires', 'Sat, 01 Jan 2000 00:00:00 GMT');
    return headers;
  }


  private onSuccess<T>(response: Response): any {
    // map response in different way on custom objects
    return response;
  }

  private onError(response: Response): any {
    console.error(response);
    // logs toasters
    return Observable.throw(response);
  }
}
