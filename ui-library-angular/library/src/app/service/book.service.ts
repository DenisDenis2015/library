import {Injectable, NgZone} from '@angular/core';
import {IBookModel, BookModel} from "../model/book-model";
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs/Rx";
import {IGenreModel, GenreModel} from "../model/genre-model";
import {Store} from "@ngrx/store";
import {
  map,
  debounceTime,
  distinctUntilChanged,
  switchMap,
  tap
} from "rxjs/operators";
import {BooksState} from "../model/AppState";
import {LoadBooksAction} from "../store/action/booksAction";

const EventSource: any = window['EventSource'];

const httpOptions = {
  headers: new HttpHeaders({
    'Connection': 'keep-alive',
    'Content-Type': 'text/event-stream',
    'Cache-Control': 'no-cache'
  })
};

@Injectable()
export class BookService {

  constructor(private http: HttpClient, private ngZone: NgZone) {
  }

  getGenres(): Observable<IGenreModel[]> {
    return new Observable<IGenreModel[]>(obs => {

      const eventSource = new EventSource('http://localhost:9991/books-list/get/all/genres', httpOptions);

      eventSource.onmessage = event => {
        this.ngZone.run(() => obs.next(JSON.parse(event.data)));
      };

      eventSource.onerror = error => {
        console.log(error);
        eventSource.close();
      };

      return () => eventSource.close();
    });
  }

  getBooksList(): Observable<IBookModel[]> {
    return new Observable<IBookModel[]>(obs => {

      const eventSource = new EventSource('http://localhost:9991/books-list/get/all/books', httpOptions);

      eventSource.onmessage = event => {
        this.ngZone.run(() => obs.next(JSON.parse(event.data)));
      };

      eventSource.onerror = error => {
        console.log(error);
        eventSource.close();
      };

      return () => eventSource.close();
    });
  }
}
