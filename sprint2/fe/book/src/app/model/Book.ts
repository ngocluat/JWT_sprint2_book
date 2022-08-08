import {Author} from "./Author";
import {Category} from "./Category";

export interface Book {
  bookId: number;
  bookName: string;
  bookImage: string;
  bookPrice: number;
  bookTranslator: string;
  publishingCompany: string;
  bookNumberOfPages: number;
  bookReleaseDate: string;
  bookDescription: string;
  discount: number;
  postBy: string;
  author: Author;
  category: Category;
  newChapter: string;
}
