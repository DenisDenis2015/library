import {Injectable} from '@angular/core';
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

const EventSource: any = window['EventSource'];

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BookService {

  constructor(private http: HttpClient) {}

  getGenres(): Observable<IGenreModel[]> {
    return Observable.create((observer) => {
      const eventSource = new EventSource('http://localhost:9991/books-list/get/all/genres');
      eventSource.onmessage = (event) => {
        const genre = JSON.parse(event.data);
        observer.next(new GenreModel(
          genre['id'], genre['genre']));
      };
      eventSource.onerror = (error) => observer.error('eventSource.onerror: ' + error);
      return () => eventSource.close();
    });
  }

  getBooksList(): Observable<IBookModel[]> {
    return Observable.create((observer) => {
      const eventSource = new EventSource('http://localhost:9991/books-list/get/all/books', httpOptions);
      eventSource.onmessage = (event) => {
        const book = JSON.parse(event.data);
        observer.next([new BookModel(
          book['id'], book['title'], book['author'], book['description'], book['genre'], book['year']
        )]);
      };

      eventSource.onerror = (error) => observer.error('eventSource.onerror: ' + error);
      return () => eventSource.close();
    });
  }

  getBooksList2(): Observable<IBookModel[]> {
    return this.http.get('http://localhost:9991/books-list/get/all/books').pipe(
      map(res => {
        return JSON.parse(res.toString()).results.map(item =>{
            return new BookModel(
              item['id'], item['title'], item['author'], item['description'], item['genre'], item['year']
            )
        })
      })
    )
  }

  getBooksList3(): Observable<Object> {
    return this.http.get('http://localhost:9991/books-list/get/all/books', httpOptions);
  }

}
