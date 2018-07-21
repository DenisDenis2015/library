export interface IGenreModel {
  id:String;
  genre:String;
}

export class GenreModel implements IGenreModel {
  id:String;
  genre:String;

  constructor (id:String, genre:String) {
    this.id = id;
    this.genre = genre;
  }
}
