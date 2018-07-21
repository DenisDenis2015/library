import {Action} from "@ngrx/store/store";
import {IBookModel} from "../../model/book-model";

export const GENRE = 'Genre';
export const LOAD_BOOKS = 'Load books';


export class BooksByGenreAction implements Action {

  readonly type = GENRE;

  constructor(public payload: IBookModel[]) {}

}

export class LoadBooksAction implements Action {

  readonly type = LOAD_BOOKS;

  constructor(public payload: IBookModel[]) {}

}

export type All = BooksByGenreAction | LoadBooksAction;

