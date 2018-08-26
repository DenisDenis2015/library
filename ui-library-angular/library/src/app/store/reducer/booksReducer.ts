import {createFeatureSelector, createSelector} from '@ngrx/store';
import {BooksState} from '../../model/AppState';
import * as fromActions from '../action/booksAction';
import {BookModel, IBookModel} from '../../model/book-model';
import {GenreModel, IGenreModel} from '../../model/genre-model';


export const initialState: BooksState = {books: [], genres: []};

export function reducer(state = initialState, action: fromActions.All): BooksState {

  switch (action.type) {

    case fromActions.LOAD_BOOKS_BY_GENRE: {
      const newBook: IBookModel[] = action.payload.books;
      return {
        books: state.books.concat(action.payload.books),
        genres: state.genres
      };
    }

    case fromActions.LOAD_BOOKS: {

      const newBook: IBookModel[] = action.payload.books;

      return {
        books: state.books.concat(newBook),
        genres: state.genres
      };
    }

    case fromActions.LOAD_GENRES: {

      const newGenres: IGenreModel[] = action.payload.genres;

      return {
        books: state.books,
        genres: state.genres.concat(newGenres),
      };
    }

    case fromActions.CLEAR_BOOK_STORE: {
      return {
        books: [],
        genres: state.genres,
      };
    }

    case fromActions.ADD_NEW_BOOK_STORE: {
      let book: IBookModel = new BookModel(null, "", "", "", new GenreModel("", ""), "");
      return {
        books: state.books.concat(book),
        genres: state.genres
      }
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
  (state: BooksState) => state.genres
);
