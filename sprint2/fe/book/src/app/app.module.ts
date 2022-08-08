import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from "./login/login.component";
import {CartComponent} from './cart/cart.component';
import {DetailComponent} from './detail/detail.component';
import {IndexComponent} from './index/index.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {ErrorsComponent} from './errors/errors.component';
import {HomeBookComponent} from './main-event/home-book/home-book.component';
import {HttpClientModule} from "@angular/common/http";
import {ToastrModule} from "ngx-toastr";
import {ReactiveFormsModule} from "@angular/forms";
import {AdminComponent} from './admin/admin.component';
import {authInterceptorProviders} from "./service/security/auth.interceptor";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CartComponent,
    DetailComponent,
    IndexComponent,
    SignUpComponent,
    HeaderComponent,
    FooterComponent,
    ErrorsComponent,
    HomeBookComponent,
    LoginComponent,
    AdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
