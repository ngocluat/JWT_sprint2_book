import {Injectable} from '@angular/core';
import {CartDetailDto} from "../dto/CartDetailDto";
import {BookDtoForCart} from "../dto/BookDtoForCart";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor() {
  }


  addToCart(book: BookDtoForCart, quantity: number) {
    let cartDetailDtos: CartDetailDto[] = [];
    if (localStorage.getItem('cart')) {
      cartDetailDtos = JSON.parse(localStorage.getItem('cart'));
      console.log(cartDetailDtos)
    }
    let exists = false;

    cartDetailDtos.forEach(item => {
      if (item.book.bookId == book.bookId) {
        exists = true;
        if (quantity < 1 && item.quantity == 1) {
          item.quantity = 1;
        } else {
          item.quantity += quantity;
        }
      }
    });
    if (!exists && quantity > 0) {
      let cartDetailDto = {} as CartDetailDto;
      cartDetailDto.quantity = quantity;
      cartDetailDto.book = book;
      cartDetailDtos.push(cartDetailDto);
    }
    localStorage.setItem('cart', JSON.stringify(cartDetailDtos));
  }




  getCart(): CartDetailDto[] {
    return JSON.parse(localStorage.getItem('cart'));
  }

  removeItemFromCart(medicine: BookDtoForCart) {
    let cartDetailDtos: CartDetailDto[] = [];
    if (localStorage.getItem('cart')) {
      cartDetailDtos = JSON.parse(localStorage.getItem('cart'));
    }
    cartDetailDtos = cartDetailDtos.filter(item => item.book.bookId !== medicine.bookId);
    localStorage.setItem('cart', JSON.stringify(cartDetailDtos));
  }

  clearCart() {
    localStorage.removeItem('cart');
  }

}
