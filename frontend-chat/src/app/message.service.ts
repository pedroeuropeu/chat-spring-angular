import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  private stompClient: any;
  public msg = [] as any;
  
  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function(frame: any) {
      that.stompClient.subscribe('/message', (message: any) => {
        if (message.body) {
          that.msg.push(message.body);
        }
      });
    });
  }

  sendMessage(message: string) {
    this.stompClient.send('/app/send/message' , {}, message);
  }
}
