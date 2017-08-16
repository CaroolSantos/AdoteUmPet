import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';
import { ContasServicoProvider } from '../../providers/contas-servico/contas-servico';
import { NativeStorage } from '@ionic-native/native-storage';
import { HomePage } from '../home/home';

@IonicPage()
@Component({
  selector: 'page-cadastro-usuario',
  templateUrl: 'cadastro-usuario.html',
})
export class CadastroUsuarioPage {
  user = {};

  constructor(public navCtrl: NavController, public accountService: ContasServicoProvider,
    public storage: NativeStorage) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroUsuarioPage');
  }

  registerUser(){
    this.accountService.registerUser(this.user)
      .subscribe(
        data => {
          console.log('INFO - registerUser > CadastroUsuarioPage ' + JSON.stringify(data));
          //retorna ok, redireciona para o endpoint token e salva accesToken e refreshToken
          this.accountService.login(this.user)
            .subscribe(
              data => {
                console.log('INFO - login > registerUser > CadastroUsuarioPage ' + JSON.stringify(data));
                //salva acces token e refresh token
                this.storage.setItem('access_token',data.access_token);
                this.storage.setItem('refresh_token', data.refresh_token);
                this.navCtrl.setRoot(HomePage);
              },
              err => {
                console.log('ERROR - login > registerUser > CadastroUsuarioPage ' + JSON.stringify(err));
              }
            )
        },
        err => {
          console.log('ERROR - registerUser > CadastroUsuarioPage ' + JSON.stringify(err));
        }
      )
  }

}
