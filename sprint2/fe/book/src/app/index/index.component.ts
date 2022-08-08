import {Component, OnInit} from '@angular/core';
import {BookService} from "../service/book.service";
import {Book} from "../model/Book";
import {CartService} from "../service/cart.service";
import {BookDtoForCart} from "../dto/BookDtoForCart";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  bookDiscount: Book[];
  bookDontDiscount: Book[];
  totalPages: number;
  currentPage: number = 0;

  totalPagesNDis: number;
  currentPageNDis: number = 0;

  constructor(private bookService: BookService,
              private cartService: CartService,
              private toastr: ToastrService) {
    scrollBy(0, 0);
  }

  ngOnInit(): void {
    this.getListBook({})
    this.findAllByDiscountNull({})
  }

  x

  private getListBook(request) {
    this.bookService.getAll(request).subscribe(data => {
        if (data !== null) {
          this.bookDiscount = data.content;
          this.currentPage = data.number;
          this.totalPages = data.totalPages;
          console.log("discount")
          console.log(data)
        } else {
          this.bookDiscount = [];
        }
      }, error => {
        this.bookDiscount.length = 0;
      }, () => {
      }
    );
  }

  private findAllByDiscountNull(request) {
    this.bookService.getAllDontDiscount(request).subscribe(data => {
        if (data !== null) {
          this.bookDontDiscount = data.content;
          this.currentPageNDis = data.number;
          this.totalPagesNDis = data.totalPages;
          console.log(" not discount")
          console.log(data)
        } else {
          this.bookDiscount = [];
        }
      }, error => {
        this.bookDiscount.length = 0;
      }, () => {
      }
    );
  }

  getRandomInt(max) {
    return Math.floor(Math.random() * max);
  }


  public previousPageDontDiscout() {
    const request = {};
    if ((this.currentPageNDis) > 0) {
      // @ts-ignore
      request.page = this.currentPageNDis - 1;
      // @ts-ignore
      request.size = 10;
      // @ts-ignore
      this.findAllByDiscountNull(request);
    }
  }

  public nextPageDontDiscout() {
    const request = {};
    if ((this.currentPageNDis + 1) < this.totalPagesNDis) {
      // @ts-ignore
      request.page = this.currentPageNDis + 1;
      // @ts-ignore
      request.size = 10;
      this.findAllByDiscountNull(request);
    }
  }


  public previousPageDiscout() {
    const request = {};
    if ((this.currentPage) > 0) {
      // @ts-ignore
      request.page = this.currentPage - 1;
      // @ts-ignore
      request.size = 10;
      // @ts-ignore
      this.getListBook(request);
    }
  }

  public nextPageDiscout() {
    const request = {};
    if ((this.currentPage + 1) < this.totalPages) {
      // @ts-ignore
      request.page = this.currentPage + 1;
      // @ts-ignore
      request.size = 10;
      this.getListBook(request);
    }
  }

  addItemToCart(i: BookDtoForCart) {
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
