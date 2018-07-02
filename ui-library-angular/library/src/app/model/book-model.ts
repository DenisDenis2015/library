import {GenreModel} from "./genre-model";

export interface BookModel {
  id:String;
  title:String;
  author:String;
  description:String;
  genre:GenreModel;
  year:String;
}
