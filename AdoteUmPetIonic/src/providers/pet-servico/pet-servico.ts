import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

/*
  Generated class for the PetServicoProvider provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular DI.
*/
@Injectable()
export class PetServicoProvider {

  constructor(public http: Http) {
    console.log('Hello PetServicoProvider Provider');
  }

  salvarPet(pet){
     return this.http.post("", JSON.stringify(pet))
      .map(res=>{res});
  }


  carregarPets(){
    return this.http.get("")
      .map(res=>res.json())
      .catch(res=>{return Observable.throw(res)});
  }
}
