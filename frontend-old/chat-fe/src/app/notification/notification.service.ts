import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/user';
import { pathValues } from '../../utils';
import { EmailSystem, Notification } from '../../model';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  
  constructor(private http: HttpClient) {}

  listNotificationByUserDestinyAndStatus(user: User, view: boolean): Observable<Notification> {
    const params = { 
      "idUserDestiny": user.id.toString(),
      "viewed": view.toString()
    };
    return this.http.get<Notification>(pathValues.listNotificationByUserDestinyAndStatus, {
      params: params
    });
  }

  listNotificationByUserDestiny(user: User): Observable<Notification> {
    const params = { 
      "idUserDestiny": user.id.toString()
    };
    return this.http.get<Notification>(pathValues.listNotificationByUserDestiny, {
      params: params
    });
  }

  reSentEmail(emailId: number): Observable<EmailSystem> {
    const params = { 
      "emailId": emailId.toString()
    };
    return this.http.get<EmailSystem>(pathValues.reSentEmail, {
      params: params
    });
  }

  listEmailsByFilter(emailTo: string, dateSent: string, sent: boolean): Observable<Notification> {
    const params = { 
      "emailTo": emailTo,
      "dateSent": dateSent,
      "sent": sent.toString()
    };
    return this.http.get<EmailSystem>(pathValues.listEmailsByFilter, {
      params: params
    });
  }

  viewNotification(idNotification: number){
    const params = { 
      "idNotification": idNotification.toString()
    };
    return this.http.get<Notification>(pathValues.viewNotification, {
      params: params
    });
  }

  sendEmailTest(email: string){
    const params = { 
      "email": email
    };
    return this.http.get(pathValues.sendEmailTest, {
      params: params
    });
  }

  sendNotificationToAdmin(notification: Notification){
    const body = {
      "description": notification.description,
      "entity": notification.entity,
      "idEntity": notification.idEntity,
      "typeNotification": notification.typeNotification
    };
    return this.http.post(pathValues.sendNotificationToAdmin, body);
  }

  sendNotificationToClient(notification: Notification){
    const body = {
      "description": notification.description,
      "entity": notification.entity,
      "idEntity": notification.idEntity,
      "typeNotification": notification.typeNotification,
      "userDestiny": notification.userDestiny
    };
    return this.http.post(pathValues.sendNotificationToClient, body);
  }

}
