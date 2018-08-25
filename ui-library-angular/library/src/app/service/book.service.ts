import {Injectable, NgZone} from '@angular/core';
import {IBookModel, BookModel} from '../model/book-model';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {IGenreModel, GenreModel} from '../model/genre-model';

const EventSource: any = window['EventSource'];

const httpOptions = {
  headers: new HttpHeaders({
    /*'Connection': 'keep-alive',*/
    'Content-Type': 'application/json',
    'Cache-Control': 'no-cache',
    'Accept' : 'text/event-stream'
  })
};

const httpOptionsJson = {
  headers: new HttpHeaders({
    /*'Connection': 'keep-alive',*/
    'Content-Type': 'application/json',
    'Cache-Control': 'no-cache',
    'Accept' : 'application/json'
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

  getBooksByGenre(genre: String): Observable<IBookModel[]> {
    return new Observable<IBookModel[]>(obs => {

      const eventSource = new EventSource('http://localhost:9991/books-list/get/all/books/' + genre, httpOptions);

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

  saveBook(book: IBookModel): Observable<IBookModel> {
    return this.http.post<IBookModel>('http://localhost:9991/books-list/save/book', book, httpOptionsJson);
  }
}
