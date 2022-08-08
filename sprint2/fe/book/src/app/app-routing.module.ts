import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {CartComponent} from "./cart/cart.component";
import {DetailComponent} from "./detail/detail.component";
import {SignUpComponent} from "./sign-up/sign-up.component";
import {IndexComponent} from "./index/index.component";
import {ErrorsComponent} from "./errors/errors.component";
import {AdminComponent} from "./admin/admin.component";
import {AuthGuard} from "./service/security/auth.guard";


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'index'},
  {path: 'login', component: LoginComponent},
  {path: 'index', component: IndexComponent},
  {path: 'cart', component: CartComponent},
  {path: 'detail/:id', component: DetailComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'error', component: ErrorsComponent},
  {
    path: 'admin/:id', component: AdminComponent, canActivate: [AuthGuard], data: {
      roles: ["ROLE_ADMIN"]
    }
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
