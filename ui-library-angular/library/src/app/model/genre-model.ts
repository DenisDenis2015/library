export interface IGenreModel {
  id: String;
  name: String;
  count: number;
}

export class GenreModel implements IGenreModel {
  id: String;
  name: String;
  count: number;

  constructor(id: String, name: String, count: number) {
    this.id = id;
    this.name = name;
    this.count = count;
  }
}
