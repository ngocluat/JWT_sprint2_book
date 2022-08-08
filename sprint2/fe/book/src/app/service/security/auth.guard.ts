import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router : Router, private tokenStorageService:TokenStorageService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url :string= state.url;
    const currentUser = this.tokenStorageService.getUser();
    if(currentUser!==null){
      let role = currentUser.roles[0];

      if(next.data.roles.indexOf(role) === -1){
        this.router.navigate(['/error'],{
          queryParams:{returnUrl : url}
        });
        return false;
      }
      return true;
    }
    return false;
  }

}
