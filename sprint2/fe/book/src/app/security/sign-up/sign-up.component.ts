import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignUpRequest} from "../../../dto/request/SignUpRequest";
import {SecurityService} from "../../../service/security/security.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

/**
 * @Author HuuNQ
 * @Time 17:00:00 04/07/2022
 * @Function Component use for sign up new customer want use online web app
 */

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  signUpForm : FormGroup;

  constructor(private service: SecurityService,private route:Router,private toast:ToastrService) { }
  errorMap : any;
  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      name : new FormControl('',[Validators.required]),
      email : new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required]),
      confirmPassword: new FormControl('',[Validators.required]),
      gender : new FormControl('0',[Validators.required]),
      address : new FormControl('',[Validators.required]),
      phone: new FormControl('',[Validators.required]),
      dayOfBirth: new FormControl('',[Validators.required]),
      note : new FormControl('')
    })
  }

  submitSignUp() {
    if(this.signUpForm.valid){
      const signUpRequest : SignUpRequest = this.signUpForm.value;
      this.service.signUp(signUpRequest).subscribe(
        next => {
          this.toast.success("Đăng ký thành công","Chúc mừng")
          this.signUpForm.reset();
          this.route.navigateByUrl('/').then()
        },error => {
          this.errorMap = error.error.errorMap
        }
      )
    }

  }

}
