import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

let apiAbrigoUrl = "http://adoteumpetwebservice.azurewebsites.net/api/Shelter";


@Injectable()
export class AbrigoServicoProvider {

  constructor(public http: Http) {
    console.log('Hello AbrigoServicoProvider Provider');
  }

  salvarAbrigo(abrigo, token) {
    let headers = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(apiAbrigoUrl, abrigo, options)
      .map(res => res.json())
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      })
  }

  carregarAbrigos() {
    return this.http.get(apiAbrigoUrl)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

  abrirAbrigo(id){
    var url = apiAbrigoUrl + "/" + id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

}
