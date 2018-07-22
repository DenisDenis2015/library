import {Action} from "@ngrx/store/store";
import {IBookModel} from "../../model/book-model";
import {IGenreModel} from "../../model/genre-model";

export const LOAD_BOOKS_BY_GENRE = 'Load books by genre';
export const LOAD_BOOKS = 'Load books';
export const LOAD_GENRES = 'Load genres';
export const CLEAR_BOOK_STORE = 'Clear book store';

export class BooksByGenreAction implements Action {

  readonly type = LOAD_BOOKS_BY_GENRE;

  constructor(public payload: { books : IBookModel[], genres : IGenreModel[]}) {}

}

export class LoadBooksAction implements Action {

  readonly type = LOAD_BOOKS;

  constructor(public payload: { books : IBookModel[], genres : IGenreModel[]}) {}

}

export class LoadGenresAction implements Action {

  readonly type = LOAD_GENRES;

  constructor(public payload: { books : IBookModel[], genres : IGenreModel[]}) {}

}

export class ClearBookStoreAction implements Action {

  readonly type = CLEAR_BOOK_STORE;

  constructor(public payload: { books : IBookModel[], genres : IGenreModel[]}) {}

}

export type All = BooksByGenreAction | LoadBooksAction | LoadGenresAction | ClearBookStoreAction;

