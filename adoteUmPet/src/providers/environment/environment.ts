import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {ENV} from '../../environment';

/*
  Generated class for the EnvironmentProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class EnvironmentProvider {

  public getApiUrlBase() : string{
    if(ENV.currentEnvironment === "development"){
        return ENV.development.ApiUrlBase;
    } else if(ENV.currentEnvironment === "production") {
        return ENV.production.ApiUrlBase;
    }
  }


}
