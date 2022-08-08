import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Book} from "../model/Book";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  public findById(id: number): Observable<Book> {
    return this.http.get<Book>(`http://localhost:8080/admin/test/random/${id}`);
  }

}
