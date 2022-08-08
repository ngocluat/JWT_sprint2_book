import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignUpComponent} from "./sign-up/sign-up.component";
import {LogoutComponent} from "./logout/logout.component";
import {AuthGuard} from "../../service/security/auth.guard";
import {ForbiddenPageComponent} from "./forbidden-page/forbidden-page.component";


const routes: Routes = [{
  path: 'sign-in',
  component: LoginComponent
},{
  path: 'sign-up',
  component: SignUpComponent
},{
  path: 'sign-out',
  component: LogoutComponent,
},
  {
    path: 'forbidden-page',
    component: ForbiddenPageComponent
  }];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogRoutingModule { }
