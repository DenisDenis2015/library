import {Component, OnInit} from '@angular/core';
import {IBookModel} from '../model/book-model';
import {BookService} from '../service/book.service';
import {Observable} from 'rxjs/Observable';
import * as fromRoot from '../store/reducer/booksReducer';
import {Store} from '@ngrx/store';
import {BooksState} from '../model/AppState';
import {LoadBooksAction} from '../store/action/booksAction';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
  providers: [BookService]
})
export class BookListComponent implements OnInit {

  books$: Observable<IBookModel[]>;

  constructor(private bookService: BookService, private store: Store<BooksState>) {
    this.books$ = store.select(fromRoot.getBooks);
  }

  ngOnInit() {
    this.bookService.getBooksList().subscribe((data: IBookModel[]) => {
      this.store.dispatch(new LoadBooksAction({books: data, genres: []}));
    });
  }




}
