import {IGenreModel} from './genre-model';

export interface IBookModel {
  id: String;
  title: String;
  author: String;
  description: String;
  genre: IGenreModel;
  year: String;
}

export interface IBookModelStream {
  data: {
    book: IBookModel
  }
}

export class BookModel implements IBookModel {
  id: String;
  title: String;
  author: String;
  description: String;
  genre: IGenreModel;
  year: String;


  constructor(id: String, title: String, author: String, description: String, genre: IGenreModel, year: String) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.description = description;
    this.genre = genre;
    this.year = year;
  }
}


