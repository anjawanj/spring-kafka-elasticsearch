import { Component } from '@angular/core';
import { WebSocketAPI } from './WebSocketAPI';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  webSocketAPI: WebSocketAPI;
  msg: any = "";
  name: string;
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new AppComponent());
    this.webSocketAPI.messageReceived$.subscribe(
      message => {
        this.msg = message;
      });    
  }

  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send(this.name);
  }

  handleMessage(message){
    this.msg = message;
  }
}