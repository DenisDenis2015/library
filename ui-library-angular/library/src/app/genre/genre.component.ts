import { Component, OnInit } from '@angular/core';
import { BookService } from "../service/book.service";
import { IGenreModel } from "../model/genre-model";
import { Store } from "@ngrx/store"
import { BooksState } from '../model/AppState';
import { BooksByGenreAction } from '../store/action/booksAction';
import * as fromRoot from "../store/reducer/booksReducer";
import { Observable } from "rxjs/Observable";
import { IBookModel } from "../model/book-model";

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.scss']
})
export class GenreComponent implements OnInit {

  genres$: Observable<IGenreModel[]>;

  constructor(private bookService: BookService, private store: Store<BooksState>) {
    //this.genres$ = store.select(fromRoot.getGenres);
  }

  genres: IGenreModel[];

  ngOnInit() {
    this.bookService.getGenres().subscribe((data: any) => {
      this.genres = data;
    });
  }

  getBookByGenre(item) {

    console.log("getBookByGenre " + item);

    this.store.dispatch(new BooksByGenreAction([{
      id: "1",
      title: "String" + Math.floor(Math.random() * 10) + 1,
      author: "String" + Math.floor(Math.random() * 10) + 1,
      description: "String" + Math.floor(Math.random() * 10) + 1,
      genre: { id: "1", genre: "action" },
      year: "String",
    }]));
  }
}
