import { Component, OnInit } from '@angular/core';
import {BookModel, IBookModel} from '../model/book-model';
import { BookService } from '../service/book.service';
import { Observable } from 'rxjs/Observable';
import * as fromRoot from '../store/reducer/booksReducer';
import { Store } from '@ngrx/store';
import { BooksState, AppState } from '../model/AppState';
import {LoadBooksAction} from "../store/action/booksAction";

export const initialState = new BookModel(
    "0",
    "00String",
    "00String",
    "00String",
    { id: "001", genre: "00action" },
    "00String");


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

/*  foo() {
    setTimeout(function() {
      that.store.dispatch(new LoadBooksAction([initialState]))
    }.bind(this), 2000);
  }*/

  ngOnInit() {
    this.store.dispatch(new LoadBooksAction([initialState]));

/*    setTimeout(function() {
      this.store.dispatch(new LoadBooksAction([initialState]))
    }.bind(this), 2000);*/


    this.bookService.getBooksList3().subscribe((data: IBookModel[]) => {
      this.store.dispatch(new LoadBooksAction(data))
    })


/*    this.bookService.getBooksList().subscribe((data: IBookModel[]) => {
      this.store.dispatch(new LoadBooksAction(data))
    });*/
  }
}
