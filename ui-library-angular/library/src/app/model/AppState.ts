import {BookModel} from "./book-model";

export interface AppState {
  bookState: BooksState;
}
export interface BooksState {
  books: BookModel[];
}
