import {Component, OnInit} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {CartDetailDto} from "../dto/CartDetailDto";
import {CartService} from "../service/cart.service";
import {TokenStorageService} from "../service/security/token-storage.service";
import {BookDtoForCart} from "../dto/BookDtoForCart";

@Component({
  selector: 'app-detail',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],

})
export class CartComponent implements OnInit {
  display = 'none';
  username: string;
  cartDetails: CartDetailDto [] = [];
  total = 0;
  constructor(private title: Title,
              private route: Router,
              private tokenStorageService: TokenStorageService,
              private cartService: CartService) {
    scrollBy(0, 0);
  }

  ngOnInit(): void {
    this.title.setTitle('Cart - Book');
    if (this.tokenStorageService.getUser() != null) {
      this.username = this.tokenStorageService.getUser().username;
      console.log(this.username);
    }
    this.cartDetails = this.cartService.getCart();
    this.total = this.getTotal();
    window.scrollBy(0, 0);
  }

  getTotal(): number {
    let total = 0;
    if (this.cartDetails != null) {
      this.cartDetails.forEach(item => {
        total += (item.quantity * item.book.bookPrice);
      });
    }
    return total;
  }

  removeItem(medicine: BookDtoForCart) {
    this.cartService.removeItemFromCart(medicine);
    this.cartDetails = this.cartService.getCart();
    this.total = this.getTotal();
  }
}
