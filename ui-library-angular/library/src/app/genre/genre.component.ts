import { Component, OnInit } from '@angular/core';
import { BookService } from '../service/book.service';
import { IGenreModel } from '../model/genre-model';
import { Store } from '@ngrx/store';
import { BooksState } from '../model/AppState';
import {BooksByGenreAction, ClearBookStoreAction, LoadBooksAction, LoadGenresAction} from '../store/action/booksAction';
import * as fromRoot from '../store/reducer/booksReducer';
import { Observable } from 'rxjs/Observable';
import { IBookModel } from '../model/book-model';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.scss']
})
export class GenreComponent implements OnInit {

  genres$: Observable<IGenreModel[]>;

  constructor(private bookService: BookService, private store: Store<BooksState>) {
    this.genres$ = store.select(fromRoot.getGenres);
  }

  ngOnInit() {
    this.bookService.getGenres().subscribe((data: IGenreModel[]) => {
      this.store.dispatch(new LoadGenresAction({books : [], genres : data}));
    });
  }

  getBookByGenre(name) {
    this.store.dispatch(new ClearBookStoreAction({books: [], genres: []}));
    this.bookService.getBooksByGenre(name).subscribe((data: IBookModel[]) => {
      this.store.dispatch(new BooksByGenreAction({books: data, genres: []}));
    });
  }

  getAllBooks() {
    this.store.dispatch(new ClearBookStoreAction({books: [], genres: []}));
    this.bookService.getBooksList().subscribe((data: IBookModel[]) => {
      this.store.dispatch(new LoadBooksAction({books : data, genres : []}));
    });
  }
}
