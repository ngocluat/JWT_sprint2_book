import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {Book} from "../model/Book";
import {BookService} from "../service/book.service";
import {CartService} from "../service/cart.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  private idBook: number;
  public book: Book;
  public view: number = 1267;

  constructor(private activatedRoute: ActivatedRoute,
              private bookService: BookService,
              private  cartService: CartService,
              private route: Router,
              private toastr: ToastrService) {
    scrollBy(0, 0);
  }

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.idBook = +paramMap.get('id');
      this.getBookDetail(this.idBook)
    });

  }


  getBookDetail(id: number) {
    return this.bookService.findById(id).subscribe(bookValue => {
      this.book = bookValue;
      console.log(bookValue)
    }, error => {
      this.route.navigateByUrl('/error');
    });
  }


  getView() {
    this.view = this.view + 1
  }

  addItemToCart(i: any) {
    this.cartService.addToCart(i, 1);
    this.showMessageSuccess(i.bookName);
  }

  showMessageSuccess(medicineName: string) {
    this.toastr.success('Đã thêm thành công ' + medicineName + ' vào giỏ hàng', 'Thông báo', {
      timeOut: 2000,
      progressBar: true,
    });
  }
}
