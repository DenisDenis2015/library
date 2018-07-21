import {IBookModel} from "./book-model";

export interface AppState {
  bookState: BooksState;
}
export interface BooksState {
  books: IBookModel[];
}
