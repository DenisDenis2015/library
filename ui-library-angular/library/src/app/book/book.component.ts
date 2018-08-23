import {Component, Input, OnInit} from '@angular/core';
import {BookModel} from '../model/book-model';
import {BookService} from '../service/book.service';
import {Store} from '@ngrx/store';
import {BooksState} from '../model/AppState';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss'],
  providers: [BookService]
})
export class BookComponent implements OnInit {

  @Input()
  book: BookModel;

  editMode;

  constructor(private bookService: BookService, private store: Store<BooksState>) {
    this.editMode = false;
  }

  ngOnInit() {
  }

  edit() {
    this.editMode = !this.editMode;
  }

  save() {
    this.bookService.saveBook(this.book);
  }

}
