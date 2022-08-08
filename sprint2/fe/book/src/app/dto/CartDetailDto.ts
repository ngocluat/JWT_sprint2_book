import {BookDtoForCart} from "./BookDtoForCart";

export interface CartDetailDto {
  book: BookDtoForCart;
  quantity: number;
}
