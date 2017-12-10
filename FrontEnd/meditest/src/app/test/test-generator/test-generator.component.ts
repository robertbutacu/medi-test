import {Component, Input, OnInit} from '@angular/core';
import {TestService} from '../test.service';

@Component({
  selector: 'app-test-generator',
  templateUrl: './test-generator.component.html',
  styleUrls: ['./test-generator.component.scss']
})
export class TestGeneratorComponent implements OnInit {
  constructor(public testService: TestService) {
  }

  ngOnInit() {
  }

  private generateTest(): void {
    // populate test list with information from backend
    this.testService.isGenerated = true;
  }
}
