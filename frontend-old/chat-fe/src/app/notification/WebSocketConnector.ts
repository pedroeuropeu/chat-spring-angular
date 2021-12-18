import { Stomp } from "@stomp/stompjs";
import * as SockJS from 'sockjs-client';

import { AppComponent } from "../app.component";

export class WebSocketConnector {

    private stompClient: any;

    constructor(private appComponent: AppComponent) {
        this.connect();
    }

    private connect() {
        console.log("Starting a WebSocket connection");
        const ws = new SockJS('http://localhost:8080/public/socket');
        this.stompClient = Stomp.over(ws);
        this.stompClient.reconnect_delay = 2000;
        this.stompClient.connect({}, frame => {
            this.stompClient.subscribe('/newNotification', message => {
                this.handlerMessageReceived(message);
            });
        }, this.onError.bind(this));
    };

    private onError(error: any) {
        console.log("Error while connect: " + error);
        setTimeout(() => {
            console.log("Trying to connect again...");
            this.connect();
        }, 3000);
    }

    handlerMessageReceived(message: any): void{
        var bodyMessage = JSON.parse(message.body);
        this.appComponent.title = bodyMessage.message;
         
    }
}
