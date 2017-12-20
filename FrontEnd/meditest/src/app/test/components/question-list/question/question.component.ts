import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {Question} from "../../../models/question.model";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit,OnChanges {

  @Input() public question: any;

  public answers: Array<any> = [
    {id: 0, answer: 'Un organ', correct: true, letter: 'a)'},
    {id: 0, answer: 'Un animal', correct: false, letter: 'b)'},
    {id: 0, answer: 'Cea mai importanta celula', correct: false, letter: 'c)'},
    {id: 0, answer: 'Obiect de uzura', correct: false, letter: 'd)'}
  ];

  public alphabets: Array<any> = [
    {id: 0, letter: 'a)'},
    {id: 0, letter: 'b)'},
    {id: 0, letter: 'c)'},
    {id: 0, letter: 'd)'},
    {id: 0, letter: 'e)'},
    {id: 0, letter: 'f)'},
    {id: 0, letter: 'g)'},
  ];

  constructor() {
  }

  ngOnInit() {
  }

  ngOnChanges() {
  }

}
