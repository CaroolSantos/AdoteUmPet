import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

/*
  Generated class for the AbrigoServicoProvider provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular DI.
*/
@Injectable()
export class AbrigoServicoProvider {

  constructor(public http: Http) {
    console.log('Hello AbrigoServicoProvider Provider');
  }

  salvarAbrigo(abrigo){
     return this.http.post("", JSON.stringify(abrigo))
      .map(res=>{res});
  }

  carregarAbrigos(){
    return this.http.get("")
      .map(res=>res.json())
      .catch(res=>{return Observable.throw(res)});
  }

}
