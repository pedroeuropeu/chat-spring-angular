import { Component, Input } from '@angular/core';
import {MessageService} from './message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'websocket-frontend';

  messageToSend: string = "";
  public msg = [] as any;

  constructor(private messageService: MessageService) {
    this.msg = messageService.msg;
  }
  
  sendMessage() {
    if (this.messageToSend != "") {
      this.messageService.sendMessage(this.messageToSend);
      this.messageToSend = "";
    }
  }
}
