import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../../service/security/token-storage.service";
import {Router} from "@angular/router";

/**
 * @Author HuuNQ
 * @Time 17:00:00 04/07/2022
 * @Function Component use for sign out and clear all session
 */
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private token:TokenStorageService,private route:Router) { }

  ngOnInit(): void {
    this.token.signOut();
    this.route.navigateByUrl('/').then();
  }

}
