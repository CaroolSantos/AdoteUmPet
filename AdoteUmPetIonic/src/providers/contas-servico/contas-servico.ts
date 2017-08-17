import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';


let registerUserUrl = 'http://adoteumpetwebservice.azurewebsites.net/api/account/register';
let loginUserUrl = 'http://adoteumpetwebservice.azurewebsites.net/token';
let getTokenUrl = 'http://adoteumpetwebservice.azurewebsites.net/token'

@Injectable()
export class ContasServicoProvider {


  constructor(public http: Http) {
    console.log('Hello ContasServicoProvider Provider');
  }

  registerUser(user) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(registerUserUrl, JSON.stringify(user), options);
  }

  login(credentials) {
    //todo ver como passar body com x-www-
    // let body = JSON.stringify({
    //   grant_type: 'password',
    //   UserName: credentials.Email,
    //   Password: credentials.Password,
    //   client_id: 'ngAuthApp'
    // });
    console.log(JSON.stringify(credentials));
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('grant_type', 'password');
    urlSearchParams.append('UserName', credentials.Email);
    urlSearchParams.append('Password', credentials.Password);
    urlSearchParams.append('client_id', 'ngAuthApp');
    let body = urlSearchParams.toString()
    console.log('INFO - login > contas-servico ' + body);
    let headers = new Headers({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(loginUserUrl, body, options)
      .map(res => res.json());
  }

  //   {
  //     "access_token": "b6AxbUCTj4ytoIFhCDZELmBR8sZHYAPlO2SMY_XhoEYGqDOvfg3dRdP0UKLlD582fXfSAVpM7forRCkhw9rnDDi_Rnl_O8g0kuZz65j_IQ59kO4eegtRdy2P1oce4Pdzs8Qfil6aKUTEh1uIbQMBiGVnh9LDmPcdsmeUAwKBlhM03QUpw5nRvFWMmFYEYJ1zBnnpzobUWMw5j4MBxIjab1KFnqdwNYb1thMtYnU73xWEbmrSCQWdnFZZTOWIdOiv6Wf2s9kNU-aZcmSOIFp0ufRgewvVLwNpusIwrULsk1NXeQEvNqlAIzphaGbQriPG",
  //     "token_type": "bearer",
  //     "expires_in": 1799,
  //     "refresh_token": "6f1a0782b2df4af399e4c8a88154f88e",
  //     "as:client_id": "ngAuthApp",
  //     "userName": "a.caroolinasantos@gmail.com",
  //     ".issued": "Wed, 16 Aug 2017 09:24:11 GMT",
  //     ".expires": "Wed, 16 Aug 2017 09:54:11 GMT"
  //  }

  getToken() {
    //todo ver como passar body com x-www-
    let body = JSON.stringify({
      grant_type: 'refresh_token',
      refresh_token: '',
      client_id: 'ngAuthApp'
    });
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded', 'Accept': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(loginUserUrl, body, options)
      .map(res => res.json());
  }


}
