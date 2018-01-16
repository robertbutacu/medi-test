import {Component, OnInit, ViewChild} from '@angular/core';
import {LoginService} from "../../services/login/login.service";

@Component({
  selector: 'sn-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  @ViewChild('registerForm') registerForm;
  public loading: boolean = false;
  public error: boolean = false;
  public success: boolean = false;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  submit(): void {
    this.loading = true;
    this.loginService.createAccount(this.registerForm.value).subscribe((res) => {
      this.loading = false;
      this.error = false;
      this.success = true;
    }, err => {
      this.error = true;
      this.loading = false;
      this.success = false;
    });
  }
}
