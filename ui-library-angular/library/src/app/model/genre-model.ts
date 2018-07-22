export interface IGenreModel {
  id:String;
  name:String;
}

export class GenreModel implements IGenreModel {
  id:String;
  name:String;

  constructor (id:String, name:String) {
    this.id = id;
    this.name = name;
  }
}
