import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../service/security/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit,AfterContentChecked {
  isSignin:boolean = false;
  username:string;

  constructor(private router: Router,
              private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()){
      this.isSignin=true;
      this.username= this.tokenStorageService.getUser().username;
      console.log(this.username);
    }
  }

  logout(e) {
    this.tokenStorageService.signOut();
    this.router.navigateByUrl("/index")

    this.tokenStorageService.getUser();
    // @ts-ignore
    this.router.navigate("/index")

  }

  ngAfterContentChecked(): void {
    this.ngOnInit()
  }


}
