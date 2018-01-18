import { Component, OnInit } from '@angular/core';
import {StatsService} from "../../services/stats.service";
import {Stat} from "../../models/Stat";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {

  memberId: number;
  stats: Array<Stat> = new Array<Stat>();

  constructor(private statsService: StatsService) { }

  ngOnInit() {
    this.memberId = Number(localStorage.getItem('id'));
    this.getStats();
  }

  getStats() {
    this.statsService.getStats(this.memberId).subscribe(
      (response) => {
        this.stats = response;
      }
    );
  }

  deleteStat(id) {
    this.statsService.deleteStat(id,this.memberId).subscribe(
      (response) => {
        this.getStats();
      }
    );
  }

}
