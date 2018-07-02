import {Injectable} from '@angular/core';
import {BookModel} from "../model/book-model";
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs/Rx";
import {GenreModel} from "../model/genre-model";

@Injectable()
export class BookService {

  constructor(private http:HttpClient) {
  }

  getBooksList() : Observable<BookModel[]>{
    return this.http.get<BookModel[]>('http://localhost:9991/api/books-list/books');
  }

  getGenres() : Observable<GenreModel[]> {
    return this.http.get<GenreModel[]>('http://localhost:9991/api/books-list/genres');
  }

}
