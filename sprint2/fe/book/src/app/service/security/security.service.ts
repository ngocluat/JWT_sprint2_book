import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {SignUpRequest} from "../../model/request/SignUpRequest";
import {SignInRequest} from "../../model/request/SignInRequest";
const API : string = 'http://localhost:8080/api';
const token =  new TokenStorageService;
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'Bearer'+token.getToken(),
  })
};
@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private http : HttpClient,private storeService: TokenStorageService) { }

  signUp(obj : SignUpRequest): Observable<any>{
    return this.http.post<any>(`${API}/manager-security/users/sign-up`,obj);
  }

  signIn(obj : SignInRequest): Observable<any>{
    return this.http.post<any>(`${API}/manager-security/users/sign-in`,obj);
  }
}
