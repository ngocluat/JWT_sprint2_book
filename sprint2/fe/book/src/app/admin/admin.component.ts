import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {BookService} from "../service/book.service";
import {Book} from "../model/Book";
import {AdminService} from "../service/admin.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  idBook: number;
  bookAdimin: Book;

  constructor(private activatedRoute: ActivatedRoute,
              private adminService: AdminService,
              private route: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.idBook = +paramMap.get('id');
      this.detailBookAdmin(this.idBook);
    });
  }

  public detailBookAdmin(id: number) {
    this.adminService.findById(id).subscribe(next => {
      this.bookAdimin = next;
      console.log("this.bookAdimin")
      console.log(this.bookAdimin)
    }, error => {
      this.route.navigateByUrl("/error") ;
      console.log("error")
      console.log(error)
    })
  }

}
