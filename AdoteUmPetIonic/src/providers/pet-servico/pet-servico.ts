import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';


let apiPetUrl = "http://adoteumpetwebservice.azurewebsites.net/api/Pet";

@Injectable()
export class PetServicoProvider {

  constructor(public http: Http) {
    console.log('Hello PetServicoProvider Provider');
  }

  salvarPet(pet, token) {

    let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(apiPetUrl, pet, options)
      .map(res => res.json())
      .catch(e => {
        if (e.status === 401) {
          return Observable.throw('Unauthorized');
        }
      })


  }


  carregarPets() {
    return this.http.get(apiPetUrl)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

  abrirPet(Id) {
    var url = apiPetUrl + "/" + Id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }
}
