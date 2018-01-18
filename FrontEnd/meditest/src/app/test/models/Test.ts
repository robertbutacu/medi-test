export class Test {
  domain: any;
  difficulty: any;
  time: any;
  duration: any;
  numberOfQuestions: any;
  questions: Array<QuestionDTO>;
}

export class QuestionDTO {
  key: string;
  answers: Array<Answer>;
}

export class Answer {
  body: any;
  isCorrect: any;
  selected: any;
  fillin: any;
  matchAnswerId: any;
  selectedAnswer: any;
  correct: any;
}
