import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatsComponent } from './components/stats/stats.component';
import {FormsModule} from "@angular/forms";
import {SharedModule} from "../shared/shared.module";
import {StatsService} from "./services/stats.service";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule
  ],
  declarations: [
    StatsComponent
  ],
  providers: [
    StatsService
  ]
})
export class StatsModule { }
