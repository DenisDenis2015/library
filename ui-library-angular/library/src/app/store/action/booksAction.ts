import {Action} from "@ngrx/store/store";
import {BookModel} from "../../model/book-model";

export const GENRE = 'Genre';

export class BooksByGenreAction implements Action {

  readonly type = GENRE;

  constructor(public payload: BookModel[]) {}

}

export type All = BooksByGenreAction;

