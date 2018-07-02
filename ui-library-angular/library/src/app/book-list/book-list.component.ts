import {Component, OnInit} from '@angular/core';
import {BookModel} from "../model/book-model";
import {BookService} from "../service/book.service";
import { Observable } from 'rxjs/Observable';
import * as fromRoot from '../store/reducer/booksReducer';
import { Store } from '@ngrx/store';
import { BooksState, AppState } from '../model/AppState';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss'],
  providers: [BookService]
})
export class BookListComponent implements OnInit {

  books$: Observable<BookModel[]>;

  constructor(private bookService:BookService, private store : Store <AppState>) {
    this.books$ = store.select(fromRoot.getBooks);
  }

  books : BookModel[];


   ngOnInit() {
    /**this.bookService.getBooksList().subscribe((data : any) =>{
      this.books = data._embedded.books;
    });**/
  }
}
