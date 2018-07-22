import { createFeatureSelector, createSelector } from '@ngrx/store';
import {BooksState} from "../../model/AppState";
import * as fromActions from "../action/booksAction"
import {BookModel, IBookModel} from "../../model/book-model";
import {IGenreModel} from "../../model/genre-model";



export const initialState: BooksState = {books: [], genres:[]};

export function reducer(state = initialState, action: fromActions.All) : BooksState {

  switch(action.type) {

    case fromActions.GENRE: {
      return {
        books: action.payload.books,
        genres: action.payload.genres
      };
    }

    case fromActions.LOAD_BOOKS: {

      const newBook: IBookModel[] = action.payload.books;

      return {
        books: state.books.concat(newBook),
        genres: action.payload.genres
      };
    }

    case fromActions.LOAD_GENRES: {

      const newGenres: IGenreModel[] = action.payload.genres;

      return {
        books: state.books,
        genres: state.genres.concat(newGenres),
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

export const getGenres = createSelector(
  getBooksState,
  (state:BooksState) => state.genres
);
