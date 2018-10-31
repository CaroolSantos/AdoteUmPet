import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { EnvironmentProvider } from '../environment/environment';



@Injectable()
export class PetProvider {

  urlBase:string;

  constructor(public http: Http, public envProvider: EnvironmentProvider) {
    console.log('Hello PetProvider Provider');
    this.urlBase = this.envProvider.getApiUrlBase() + 'Pet';
  }

  salvarPet(pet, token) {

    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(this.urlBase, pet, options)
      .map(res => res.json())
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      })


  }


  carregarPets() {
    return this.http.get(this.urlBase)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

  abrirPet(Id) {
    var url = this.urlBase + Id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

}
