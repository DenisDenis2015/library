import { createFeatureSelector, createSelector } from '@ngrx/store';
import {BooksState} from "../../model/AppState";
import * as fromActions from "../action/booksAction"

export const initialState: BooksState = {
  books: [{
    id: "0",
    title: "00String",
    author: "00String",
    description: "00String",
    genre: { id: "001", genre: "00action" },
    year: "00String",
  }]
};

export function reducer(state = initialState, action: fromActions.All) {

  switch(action.type) {

    case fromActions.GENRE: {
      return {books: action.payload};
    }

    default: {
      return state;
    }
  }
}

export const getBooksState = createFeatureSelector<BooksState>('bookState');

export const getBooks = createSelector(
  getBooksState,
  (state: BooksState) => {
    return state.books
  },
);
