export class Question {
  id: number;
  type: string;
  title: string;
  response: string;

  constructor(id,type,title,response) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.response = response;
  }
}
