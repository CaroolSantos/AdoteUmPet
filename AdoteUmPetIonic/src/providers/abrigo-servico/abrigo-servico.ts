import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

let apiAbrigoUrl = "http://localhost:53961/api/Shelter";


@Injectable()
export class AbrigoServicoProvider {

  constructor(public http: Http) {
    console.log('Hello AbrigoServicoProvider Provider');
  }

  salvarAbrigo(abrigo) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(apiAbrigoUrl, abrigo, options)
      .map(res => res.json());
  }

  carregarAbrigos() {
    return this.http.get(apiAbrigoUrl)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

  abrirAbrigo(Id){
    var url = apiAbrigoUrl + "/" + Id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

}
