import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { NativeStorage } from '@ionic-native/native-storage';

let apiPetUrl = "http://adoteumpetwebservice.azurewebsites.net/api/Pet";

@Injectable()
export class PetServicoProvider {

  constructor(public http: Http,
    private storage: NativeStorage) {
    console.log('Hello PetServicoProvider Provider');
  }

  salvarPet(pet) {
    let returnObject = {
      type: '',
      msg: ''
    };

    this.storage.getItem('access_token')
      .then((data) => {
        let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + data});
        let options = new RequestOptions({ headers: headers });

        this.http.post(apiPetUrl, pet, options)
          .map(res => res.json())
          .subscribe(
            data => {

            },
            err => {
              //todo caso retorne 401 unauthorized pegar novo token com refreshtoken
              this.storage.getItem('refresh_token')
                .then(data=>{
                  if(data){

                  }else{
                    //não possui refresh token, vai precisar reautenticar usuário
                    return 
                  }
                })
            }
          )
      })

  }


  carregarPets() {
    return this.http.get(apiPetUrl)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }

  abrirPet(id) {
    var url = apiPetUrl + "/" + id;
    return this.http.get(url)
      .map(res => res.json())
      .catch(res => { return Observable.throw(res) });
  }
}
