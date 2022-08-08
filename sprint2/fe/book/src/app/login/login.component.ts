import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignInRequest} from "../model/request/SignInRequest";
import {ToastrService} from "ngx-toastr";
import {TokenStorageService} from "../service/security/token-storage.service";
import {Router} from "@angular/router";
import {SecurityService} from "../service/security/security.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  token: string;
  userName: string;
  roles: [];
  isSignIn: boolean = false;
  errorMap:any;

  constructor(private securityService: SecurityService,
              private route: Router,
              private tokenStorageService: TokenStorageService,
              private toast: ToastrService,) {
    scrollBy(0, 0);
  }


  ngOnInit(): void {
  }

  signInForm: FormGroup = new FormGroup({
    username: new FormControl("", [Validators.required]),
    password: new FormControl("", [Validators.required])
  })


  submitSignIn() {
    if (this.signInForm.valid) {
      const username = this.signInForm.value.username;
      const password = this.signInForm.value.password;
      const signInSubmitForm: SignInRequest = {username, password};
      this.securityService.signIn(signInSubmitForm).subscribe(
        next => {
          if (this.signInForm.value.remember) {
            this.tokenStorageService.saveTokenLocal(next.token);
            this.tokenStorageService.saveUserLocal(next);
          } else {
            this.tokenStorageService.saveTokenSession(next.token)
            this.tokenStorageService.saveUserSession(next);
          }
          this.userName = this.tokenStorageService.getUser().username;
          this.roles = this.tokenStorageService.getUser().roles;
          this.isSignIn = true;
          this.toast.success("Đăng nhập thành công", "Chúc mừng", {
            timeOut: 1000, tapToDismiss: true,
          })
          this.signInForm.reset();
          this.route.navigateByUrl('/index').then();

          // this.roles.forEach(role => {
          //   if (role === 'ROLE_USER') {
          //     this.route.navigateByUrl('/home-page').then();
          //   } else {
          //     this.route.navigateByUrl('/sales-management/retail').then();
          //   }
          // })


        },
        error => {
          console.log(error);
          if(error.status == 403){
            this.toast.warning("Mật khẩu không chính xác","Lỗi Đăng Nhập");
          }else{
            if(error.error?.errorMap?.notExists){
              this.toast.warning(error.error.errorMap['notExists'],"Lỗi Đăng Nhập");
            }else{
              this.errorMap = error.error.errorMap;
            }
          }
        }
      )
    }
  }
}
