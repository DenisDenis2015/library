import { createFeatureSelector, createSelector } from '@ngrx/store';
import {BooksState} from "../../model/AppState";
import * as fromActions from "../action/booksAction"
import {BookModel, IBookModel} from "../../model/book-model";



export const initialState: BooksState = {books: []};

export function reducer(state = initialState, action: fromActions.All) : BooksState {

  switch(action.type) {

    case fromActions.GENRE: {
      return {
        books: action.payload
      };
    }

    case fromActions.LOAD_BOOKS: {

      const newBook: IBookModel[] = action.payload;

      return {
        books: state.books.concat(newBook)
      };

    }

    default: {
      return state;
    }
  }
}

export const getBooksState = createFeatureSelector<BooksState>('bookState');

export const getBooks = createSelector(
  getBooksState,
  (state: BooksState) => state.books
);
