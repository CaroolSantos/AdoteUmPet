import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

let apiPetUrl = "http://localhost:53961/api/Pet";

@Injectable()
export class PetServicoProvider {

  constructor(public http: Http) {
    console.log('Hello PetServicoProvider Provider');
  }

  salvarPet(pet){
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(apiPetUrl, pet, options)
      .map(res =>res.json());
  }


  carregarPets(){
    return this.http.get(apiPetUrl)
      .map(res=>res.json())
      .catch(res=>{return Observable.throw(res)});
  }
}
