import {Component, Input, OnInit} from '@angular/core';
import {BookModel, IBookModel} from '../model/book-model';
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

  imageSrc: any;

  editMode;

  constructor(private bookService: BookService, private store: Store<BooksState>) {
    this.editMode = false;
  }

  ngOnInit() {
    if (this.book.id === null || this.book.id === '') {
      this.editMode = true
    } else if (this.book.id != null) {
      this.bookService.getImage(this.book.id).subscribe((img: any) => {
        this.imageSrc = 'data:image/jpeg;base64,' + img.data
      })
    }
  }

  edit() {
    this.editMode = !this.editMode;
  }

  save() {
    this.bookService.saveBook(this.book)
      .subscribe((result: IBookModel) => {
        this.editMode = false;
        this.book = result;
      })
  }

  read() {
    this.bookService.getPdfContent(this.book.id).subscribe((content: any) => {
      let newPdfWindow = window.open("localhot:4200/", "Print");
      let contentW = encodeURIComponent(content.data);
      let iframeStart = "<iframe width='100%' height='100%' src='data:application/pdf;base64, ";
      let iframeEnd = "'></iframe>";
      newPdfWindow.document.write(iframeStart + contentW + iframeEnd);
    })
  }
}
