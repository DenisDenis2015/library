import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {GenreComponent} from './genre/genre.component';
import {SearchStringComponent} from './search-string/search-string.component';
import {BookListComponent} from './book-list/book-list.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ButtonModule, CardModule} from 'primeng/primeng';
import {BookService} from './service/book.service';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { metaReducers, reducers} from './store/reducer/reducers';
import { BookComponent } from './book/book.component';
import {FormsModule} from '@angular/forms';


@NgModule({
  exports: [
    BrowserAnimationsModule,
    ButtonModule,
    CardModule
  ]
})
export class PrimeNGModule {
}

@NgModule({
  declarations: [
    AppComponent,
    GenreComponent,
    SearchStringComponent,
    BookListComponent,
    BookComponent
  ],
  imports: [
    BrowserModule,
    PrimeNGModule,
    HttpClientModule,
    FormsModule,
    StoreModule.forRoot(reducers , { metaReducers })
  ],
  providers: [BookService],
  entryComponents: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
