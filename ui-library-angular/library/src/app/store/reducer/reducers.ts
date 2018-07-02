import { ActionReducerMap, ActionReducer, MetaReducer } from '@ngrx/store';
import * as booksReducer from './booksReducer';
import { environment } from '../../../environments/environment';
import {AppState} from "../../model/AppState";

export const reducers: ActionReducerMap<AppState> = {
  bookState: booksReducer.reducer
};

export function logger(reducer: ActionReducer<AppState>): ActionReducer<AppState> {
  return function(state: AppState, action: any): AppState {
    console.log('state', state);
    console.log('action', action);
    return reducer(state, action);
  };
}

export const metaReducers: MetaReducer<AppState>[] = !environment.production  ? [logger]  : [];
