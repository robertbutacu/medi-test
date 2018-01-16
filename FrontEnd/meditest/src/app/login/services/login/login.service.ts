import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PathprefixService} from "../../../shared/services/pathprefix.service";

@Injectable()
export class LoginService {

  private loginUrl: string = this.pathprefixService.prefix + '/v1/user/login';
  private registerUrl: string = this.pathprefixService.prefix + '/v1/user/register';
  private createTokenURL: string = '/api/token';

  constructor(private http: HttpClient, private pathprefixService: PathprefixService) { }

  createAccount(account) {
    return this.http.post(this.registerUrl, account);
  }

  login(loginModel) {
    return this.http.post(this.loginUrl, loginModel);
  }

}
