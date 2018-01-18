import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {Question} from "../../../models/question.model";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit {

  @Input() public question: any;

  public answers: Array<any> = [
    {id: 0, answer: 'Un organ', correct: true, letter: 'a)'},
    {id: 0, answer: 'Un animal', correct: false, letter: 'b)'},
    {id: 0, answer: 'Cea mai importanta celula', correct: false, letter: 'c)'},
    {id: 0, answer: 'Obiect de uzura', correct: false, letter: 'd)'}
  ];

  public alphabets: Array<any> = [
    {id: 0, letter: '0)'},
    {id: 0, letter: '1)'},
    {id: 0, letter: '2)'},
    {id: 0, letter: '3)'},
    {id: 0, letter: '4)'},
    {id: 0, letter: '5)'},
    {id: 0, letter: '6)'},
  ];

  constructor() {
  }

  ngOnInit() {
  }

  updateAnswered() {
    this.question.value.answered = true;
  }

}
