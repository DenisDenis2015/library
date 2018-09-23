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

  downloadBook() {
    this.bookService.getPdfContent(this.book.id).subscribe((content: any) => {
      var file = new Blob([content], {type: 'application/pdf;base64'});
      const data = window.URL.createObjectURL(file);
      var link = document.createElement('a');
      link.href = data;
      link.download = this.book.title + ".pdf";
      link.click();
      setTimeout(function () {
        // For Firefox it is necessary to delay revoking the ObjectURL
        window.URL.revokeObjectURL(data); 100
      })
    })
  }
}
