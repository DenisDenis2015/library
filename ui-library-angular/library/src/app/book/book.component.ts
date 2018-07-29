import {Component, Input, OnInit} from '@angular/core';
import {BookModel} from '../model/book-model';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {

  @Input()
  book: BookModel;

  constructor() {
  }

  ngOnInit() {
  }

}
