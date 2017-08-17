import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, LoadingController } from 'ionic-angular';
import { UsuarioProvider } from '../../providers/usuario/usuario';
import { NativeStorage } from '@ionic-native/native-storage';
import { ContasServicoProvider } from '../../providers/contas-servico/contas-servico';
import { LoginPage } from '../login/login';

@IonicPage()
@Component({
  selector: 'page-perfil',
  templateUrl: 'perfil.html',
})
export class PerfilPage {
  loader: any;
  pets: any;
  abrigos: any;
  profile: any;
  tipo = "meusPets";

  constructor(public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController,
    public usuarioServico: UsuarioProvider,
    public storage: NativeStorage,
    public accountService: ContasServicoProvider,
    public alertCtrl: AlertController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad PerfilPage');
    this.loadProfile();
    
  }

  loadProfile() {
    this.presentLoading();

    this.storage.getItem('access_token')
      .then(data => {
        console.log('INFO - access token ' + data);
        this.usuarioServico.getProfile(data)
          .subscribe(data => {
            console.log('INFO - profile carregado ' + JSON.stringify(data));
            this.profile = data;
            this.loader.dismiss();
          }, err => {
            if (err === 'Unauthorized') {
              //access_token expirou, pegar novos tokens com refresh_token
              this.storage.getItem('refresh_token')
                .then(
                data => {
                  this.accountService.getToken(data)
                    .subscribe(data => {
                      //sobrescreve access e refresh token
                      this.storage.setItem('access_token', data.access_token);
                      this.storage.setItem('refresh_token', data.refresh_token);
                      this.storage.setItem('username', data.userName)
                      this.loader.dismiss();
                      this.loadProfile();

                    }, err => {
                      this.loader.dismiss();
                      //precisa autenticar novamente para atualizar tokens
                      this.exibirAlert('Usuário não autenticado', 'Suas credenciais expiraram, por favor realize o login novamente');
                      this.navCtrl.setRoot(LoginPage);
                    })
                },
                err => {
                  this.loader.dismiss()
                  //caso não possua mais refresh_token usuário precisa logar novamente
                  this.exibirAlert('Usuário não autenticado', 'Suas credenciais expiraram, por favor realize o login novamente');
                  this.navCtrl.setRoot(LoginPage);
                })

            } else {
              this.loader.dismiss();
              this.exibirAlert("Erro!", "Ocorreu um erro ao carregar o perfil, tente novamente.");
            }
          })
      })
  }

  presentLoading() {

    this.loader = this.loadingCtrl.create({
      content: "Carregando perfil..."
    });

    this.loader.present();

  }

  exibirAlert(titulo, subtitulo) {
    let alert = this.alertCtrl.create({
      title: titulo,
      subTitle: subtitulo,
      buttons: ['OK']
    });
    alert.present();

  }

  abrirPerfilAbrigo(id){
    this.navCtrl.push('PerfilAbrigoPage', {id: id})
  }

  abrirPerfilPet(id){
    this.navCtrl.push('PerfilPetPage', {id: id})
  }

}
