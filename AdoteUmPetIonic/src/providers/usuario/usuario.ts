import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

let userProfileUrl = 'http://adoteumpetwebservice.azurewebsites.net/api/UserProfile';

@Injectable()
export class UsuarioProvider {

  
  constructor(public http: Http) {
    console.log('Hello UsuarioProvider Provider');
  }

  getProfile(token){
    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token });
    let options = new RequestOptions({ headers: headers });

    return this.http.get(userProfileUrl,options)
      .map(res=>res.json())
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      })
  }
}
