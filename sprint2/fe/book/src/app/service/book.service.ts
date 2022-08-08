import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Book} from "../model/Book";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {
  }

  public getAll(request): Observable<any> {
    const params = request;
    return this.http.get<any>(`http://localhost:8080/api/manager-book`, {params});
  }

 public getAllDontDiscount(request): Observable<any> {
    const params = request;
    return this.http.get<any>(`http://localhost:8080/api/manager-book/dont-discount`, {params});
  }

  public findById(id: number): Observable<Book> {
    return this.http.get<Book>(`http://localhost:8080/api/manager-book/${id}`);
  }

}
