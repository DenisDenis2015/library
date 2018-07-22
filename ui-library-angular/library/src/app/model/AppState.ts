import {IBookModel} from "./book-model";
import {IGenreModel} from "./genre-model";

export interface AppState {
  bookState: BooksState;
}
export interface BooksState {
  books: IBookModel[];
  genres : IGenreModel[];
}
