import {Component, Input, OnInit} from '@angular/core';
import {TestService} from '../../services/test.service';
import {Test} from "../../models/Test";
import {Router} from "@angular/router";

@Component({
  selector: 'app-test-generator',
  templateUrl: './test-generator.component.html',
  styleUrls: ['./test-generator.component.scss']
})
export class TestGeneratorComponent implements OnInit {
  public opValue: string;
  public title: string = 'MediTest';
  test: Test = new Test();

  fields = [
    {value: 'Cutia craniana', viewValue: 'Cutia craniana'},
    {value: 'Urechea', viewValue: 'Urechea'},
    {value: 'Inima', viewValue: 'Inima'},
    {value: 'Schelet', viewValue: 'Schelet'},
    {value: 'Circulatia sangelui', viewValue: 'Circulatia sangelui'},
    {value: 'Maduva spinarii', viewValue: 'Maduva spinarii'}
  ];

  options = [
    {value: 'level-time-0', viewValue: 'Dificultate si durata'},
    {value: 'level-questions-1', viewValue: 'Dificultate si numar de intrebari'},
    {value: 'questions-time-2', viewValue: 'Numar de intrebari si timp'}
  ];

  levels = [
    {value: 'Easy', viewValue: 'Usor'},
    {value: 'Medium', viewValue: 'Mediu'},
    {value: 'Hard', viewValue: 'Greu'}
  ];

  times = [
    {value: '5', viewValue: '5 Minute'},
    {value: '10', viewValue: '10 Minute'},
    {value: '15', viewValue: '15 Minute'},
    {value: '20', viewValue: '20 Minute'},
    {value: '25', viewValue: '25 Minute'},
    {value: '30', viewValue: '30 Minute'},
    {value: '35', viewValue: '35 Minute'},
    {value: '40', viewValue: '40 Minute'},
    {value: '45', viewValue: '45 Minute'},
    {value: '50', viewValue: '50 Minute'},
    {value: '55', viewValue: '55 Minute'},
    {value: '60', viewValue: '60 Minute'}
  ];

  questions = [
    {value: '6', viewValue: '6 intrebari'},
    {value: '7', viewValue: '7 intrebari'},
    {value: '8', viewValue: '8 intrebari'},
    {value: '9', viewValue: '9 intrebari'},
    {value: '10', viewValue: '10 intrebari'},
    {value: '11', viewValue: '11 intrebari'},
    {value: '12', viewValue: '12 intrebari'},
    {value: '13', viewValue: '13 intrebari'},
    {value: '14', viewValue: '14 intrebari'},
    {value: '15', viewValue: '15 intrebari'},
    {value: '16', viewValue: '16 intrebari'},
    {value: '17', viewValue: '17 intrebari'},
    {value: '18', viewValue: '18 intrebari'},
    {value: '19', viewValue: '19 intrebari'},
    {value: '20', viewValue: '20 intrebari'},
    {value: '21', viewValue: '21 intrebari'},
    {value: '22', viewValue: '22 intrebari'},
    {value: '23', viewValue: '23 intrebari'},
    {value: '24', viewValue: '24 intrebari'}
  ];

  constructor(public testService: TestService,
              private router: Router) {
  }

  ngOnInit() {
  }

  private generateTest(): void {
    localStorage.setItem('domain', this.test.domain);
    localStorage.setItem('difficulty', this.test.difficulty);
    localStorage.setItem('time', this.test.time);
    localStorage.setItem('questions', this.test.numberOfQuestions.toString());
    if (this.opValue === 'level-time-0') {
      localStorage.setItem('type', '0');
    } else if (this.opValue === 'level-questions-1') {
      localStorage.setItem('type', '1');
    } else {
      localStorage.setItem('type', '2');
    }
    this.router.navigateByUrl('newtest');
  }

  setValue(value: string): void {
    console.log(value);
    this.opValue = value;
  }

}
