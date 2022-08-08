import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";

@Component({
  selector: 'app-forbidden-page',
  templateUrl: './forbidden-page.component.html',
  styleUrls: ['./forbidden-page.component.css']
})
export class ForbiddenPageComponent implements OnInit {

  constructor(private location : Location) { }

  ngOnInit(): void {
  }

  back() {
    this.location.back()
  }
}
