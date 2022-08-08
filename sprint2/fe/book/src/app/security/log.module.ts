import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LogRoutingModule } from './log-routing.module';
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {SignUpComponent} from "./sign-up/sign-up.component";
import {ReactiveFormsModule} from "@angular/forms";
import { ForbiddenPageComponent } from './forbidden-page/forbidden-page.component';


@NgModule({
  declarations: [
    LoginComponent,
    LogoutComponent,
    SignUpComponent,
    ForbiddenPageComponent,
  ],
    imports: [
        CommonModule,
        LogRoutingModule,
        ReactiveFormsModule
    ]
})
export class LogModule { }
