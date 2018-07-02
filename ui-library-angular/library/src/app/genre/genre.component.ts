import {Component, OnInit} from '@angular/core';
import {BookService} from "../service/book.service";
import {GenreModel} from "../model/genre-model";
import { Store } from "@ngrx/store"
import { BooksState } from '../model/AppState';
import { BooksByGenreAction } from '../store/action/booksAction';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.scss']
})
export class GenreComponent implements OnInit {

  constructor(private bookService:BookService, private store : Store <BooksState>) {
  }

  genres : GenreModel[];

  ngOnInit() {
    this.bookService.getGenres().subscribe((data : any) =>{
      this.genres = data._embedded.genres;
    });

    this.genres = [
      {id : "1" , genre : "action"},
      {id : "2" , genre : "fantasy"},
      {id : "3" , genre : "triller"},
      {id : "4" , genre : "fantastic"}
    ]
  }

  getBookByGenre(item){

    console.log("getBookByGenre " + item);    

    this.store.dispatch(new BooksByGenreAction([{
      id: "1" ,
      title: "String" + Math.floor(Math.random() * 10) + 1 ,
      author: "String" +  Math.floor(Math.random() * 10) + 1 ,
      description: "String" +  Math.floor(Math.random() * 10) + 1 ,
      genre: { id: "1", genre: "action" },
      year: "String",
    }]));
  }
}
